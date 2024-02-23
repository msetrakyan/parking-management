package com.parking.parkingmanagement.repository;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.constants.Role;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import com.parking.parkingmanagement.model.user.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;


@DataJpaTest
class ParkingRepositoryTest {


    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        parkingRepository.deleteAll();
    }



   @Test
   @DirtiesContext
   void  testFindByUserId_ParkingIsFound() {

       UserEntity userEntity = new UserEntity();
       userEntity.setName("Test");
       userEntity.setPassword("Password");
       userEntity.setCommunity(Community.ZONE_A);
       userEntity.setUsername("Username");
       userEntity.setRole(Role.ROLE_USER);
       userEntity.setLastname("Lastname");
       userEntity.setId(1);

       userRepository.save(userEntity);

       ParkingEntity parkingEntity = new ParkingEntity();
       parkingEntity.setUserEntity(userEntity);
       parkingEntity.setParkingNumber(10);
       parkingEntity.setCommunity(Community.ZONE_A);
       parkingEntity.setId(2);

       parkingRepository.save(parkingEntity);

       Optional<ParkingEntity> byUserId = parkingRepository.findByUserId(1);



       Assertions.assertThat(byUserId).isPresent();

       Assertions.assertThat(byUserId.get().getId()).isEqualTo(parkingEntity.getId());
   }


   @Test
   void testFindByUserId_ParkingIsNotFound() {

       Integer userId = 10;

       //given

       Optional<ParkingEntity> byUserId = parkingRepository.findByUserId(userId);

       //when

       Assertions.assertThat(byUserId).isNotPresent();

       //then
   }

   @Test
   void findAllAvailableByCommunity_foundParking() {

        ParkingEntity d = new ParkingEntity();
        d.setId(6);
        d.setCommunity(Community.ZONE_A);
        d.setParkingNumber(6);
        d.setUserEntity(null);

        parkingRepository.save(d);

       ParkingEntity b = new ParkingEntity();
       b.setId(10);
       b.setCommunity(Community.ZONE_A);
       b.setParkingNumber(10);
       b.setUserEntity(null);

       parkingRepository.save(b);

       ParkingEntity c = new ParkingEntity();
       c.setId(3);
       c.setCommunity(Community.ZONE_C);
       c.setParkingNumber(3);
       c.setUserEntity(null);

       parkingRepository.save(c);



       List<ParkingEntity> all = parkingRepository.findAll();


       List<ParkingEntity> allAvailableByCommunity = parkingRepository.findAllAvailableByCommunity(Community.ZONE_A.getName());

       Assertions.assertThat(allAvailableByCommunity.size()).isEqualTo(2);

   }



}