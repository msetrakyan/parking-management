package com.parking.parkingmanagement.mapper.parking.impl;

import com.parking.parkingmanagement.mapper.parking.ParkingMapper;
import com.parking.parkingmanagement.mapper.user.UserMapper;
import com.parking.parkingmanagement.model.parking.ParkingDto;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import com.parking.parkingmanagement.model.user.UserCreateRequest;
import com.parking.parkingmanagement.model.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingMapperImpl implements ParkingMapper {

    private final UserMapper userMapper;

    @Override
    public ParkingEntity toEntity(ParkingDto parkingDto) {

        ParkingEntity entity = new ParkingEntity();
        entity.setCommunity(parkingDto.getCommunity());
        entity.setParkingNumber(parkingDto.getParkingNumber());


        UserDto userDto = parkingDto.getUserDto();

        if(userDto != null) {
            UserCreateRequest userCreateRequest = new UserCreateRequest();
            userCreateRequest.setPassword(userDto.getPassword());
            userCreateRequest.setName(userDto.getName());
            userCreateRequest.setLastname(userDto.getLastname());
            userCreateRequest.setUsername(userDto.getUsername());
            entity.setUserEntity(userMapper.toEntity(userCreateRequest));
        }

        return entity;
    }

    @Override
    public ParkingDto toDto(ParkingEntity parkingEntity) {
        return ParkingDto.builder()
                .parkingNumber(parkingEntity.getParkingNumber())
                .community(parkingEntity.getCommunity())
                .userDto(userMapper.toDto(parkingEntity.getUserEntity()))
                .build();
    }

    @Override
    public List<ParkingDto> toParkingDtos(List<ParkingEntity> parkingEntities) {
        return parkingEntities.stream().map(this::toDto).toList();
    }
}
