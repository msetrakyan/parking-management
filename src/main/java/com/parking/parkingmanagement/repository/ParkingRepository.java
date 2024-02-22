package com.parking.parkingmanagement.repository;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Integer> {


    Optional<ParkingEntity> findByParkingNumberAndCommunity(Integer parkingNumber, Community community);

    List<ParkingEntity> findAllByCommunity(Community community);


    @Query(value = "Select p from parkings p where p.userEntity = null and p.community = ?1")
    List<ParkingEntity> findAllAvailableByCommunity(Community community);


    Optional<ParkingEntity> findByCommunityAndId(Community community, Integer parkingNumber);

    Optional<ParkingEntity> findByParkingNumber(Integer parkingNumber);


    @Query(value = "Select * from parkings where user_id = ?1", nativeQuery = true)
    Optional<ParkingEntity> findByUserId(Integer userId);

}
