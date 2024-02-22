package com.parking.parkingmanagement.service.parking;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.parking.ParkingCreateRequest;
import com.parking.parkingmanagement.model.parking.ParkingDto;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import com.parking.parkingmanagement.model.user.UserEntity;

import java.util.List;

public interface ParkingService {

    ParkingDto create(ParkingCreateRequest parkingCreateRequest);

    ParkingDto update(ParkingEntity parkingEntity);

    void delete(ParkingEntity parkingEntity);

    ParkingDto findByRowAndCommunity(Integer row, Community community);


    List<ParkingDto> findAllByCommunity(Community community);

    List<ParkingDto> availableParkingByCommunity(Community community);

    ParkingDto park(Integer parkingId);

    void release();



}
