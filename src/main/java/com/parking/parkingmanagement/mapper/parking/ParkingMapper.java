package com.parking.parkingmanagement.mapper.parking;


import com.parking.parkingmanagement.model.parking.ParkingDto;
import com.parking.parkingmanagement.model.parking.ParkingEntity;

import java.util.List;

public interface ParkingMapper {

    ParkingEntity toEntity(ParkingDto parkingDto);

    ParkingDto toDto(ParkingEntity parkingEntity);

    List<ParkingDto> toParkingDtos(List<ParkingEntity> parkingEntities);


}
