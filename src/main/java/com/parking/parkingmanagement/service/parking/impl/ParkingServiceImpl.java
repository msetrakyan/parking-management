package com.parking.parkingmanagement.service.parking.impl;


import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.exception.exceptions.DuplicationException;
import com.parking.parkingmanagement.exception.exceptions.ParkingNotFoundException;
import com.parking.parkingmanagement.exception.exceptions.ParkingSpotNotAvailableException;
import com.parking.parkingmanagement.exception.exceptions.UserNotFoundException;
import com.parking.parkingmanagement.mapper.parking.ParkingMapper;
import com.parking.parkingmanagement.model.parking.ParkingCreateRequest;
import com.parking.parkingmanagement.model.parking.ParkingDto;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import com.parking.parkingmanagement.model.user.UserEntity;
import com.parking.parkingmanagement.repository.ParkingRepository;
import com.parking.parkingmanagement.repository.UserRepository;
import com.parking.parkingmanagement.service.parking.ParkingService;
import com.parking.parkingmanagement.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public ParkingDto create(ParkingCreateRequest parkingCreateRequest) {

        if(parkingRepository.findByParkingNumberAndCommunity(parkingCreateRequest.getParkingNumber(), parkingCreateRequest.getCommunity()).isPresent()) {
            throw new DuplicationException(String.format("Parking by number %d already exists", parkingCreateRequest.getParkingNumber()));
        }

        ParkingEntity parkingEntity = new ParkingEntity();

        parkingEntity.setParkingNumber(parkingCreateRequest.getParkingNumber());
        parkingEntity.setCommunity(parkingCreateRequest.getCommunity());

        return parkingMapper.toDto(parkingRepository.save(parkingEntity));
    }

    @Override
    @Transactional
    public ParkingDto update(ParkingEntity parkingEntity) {

        ParkingEntity parking = parkingRepository
                .findById(parkingEntity.getId())
                    .orElseThrow(() -> new ParkingNotFoundException(String
                        .format("Parking by id %d not found", parkingEntity.getId())));

        parking.setCommunity(parkingEntity.getCommunity());
        parking.setParkingNumber(parking.getParkingNumber());

        return parkingMapper.toDto(parkingRepository.save(parking));
    }

    @Override
    @Transactional
    public void delete(ParkingEntity parkingEntity) {
        Optional<ParkingEntity> byParkingNumberAndCommunity = parkingRepository.findByParkingNumberAndCommunity(parkingEntity.getParkingNumber(), CurrentUser.getCommunity());
        byParkingNumberAndCommunity.ifPresent(parkingRepository::delete);
    }

    @Override
    @Transactional
    public ParkingDto findByRowAndCommunity(Integer parkingNumber, Community community) {
        return parkingMapper
                .toDto(parkingRepository
                    .findByParkingNumberAndCommunity(parkingNumber, community)
                        .orElseThrow(() -> new ParkingNotFoundException("Parking does not exist")));
    }

    @Override
    @Transactional
    public List<ParkingDto> findAllByCommunity(Community community) {
        return parkingMapper.toParkingDtos(parkingRepository.findAllByCommunity(community));
    }

    @Override
    @Transactional
    public List<ParkingDto> availableParkingByCommunity(Community community) {
        return parkingMapper.toParkingDtos(parkingRepository.findAllAvailableByCommunity(community.getName()));
    }

    @Override
    @Transactional
    public ParkingDto park(Integer parkingId) {

        UserEntity userEntity = userRepository.findById(CurrentUser.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));


        ParkingEntity parkingEntity = parkingRepository.findByParkingNumberAndCommunity(parkingId, userEntity.getCommunity()).orElseThrow(() -> new ParkingNotFoundException("Parking spot is not available"));
        if(parkingEntity.getUserEntity() != null) {
            throw new ParkingSpotNotAvailableException("Parking spot is not available");
        }
        parkingEntity.setUserEntity(userEntity);
        parkingRepository.save(parkingEntity);

        return parkingMapper.toDto(parkingEntity);
    }

    @Override
    @Transactional
    public void release() {
        Optional<ParkingEntity> byId = parkingRepository.findByUserId(CurrentUser.getId());
        if(byId.isPresent()) {
            ParkingEntity parkingEntity = byId.get();
            parkingEntity.setUserEntity(null);
            parkingRepository.save(parkingEntity);
        }
    }


}
