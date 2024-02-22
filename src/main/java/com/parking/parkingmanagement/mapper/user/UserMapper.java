package com.parking.parkingmanagement.mapper.user;

import com.parking.parkingmanagement.model.user.UserCreateRequest;
import com.parking.parkingmanagement.model.user.UserDto;
import com.parking.parkingmanagement.model.user.UserEntity;

import java.util.List;

public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserCreateRequest userCreateRequest);

    List<UserDto> toUserDto(List<UserEntity> userEntities);

//    UserCreateRequest toCreateRequest(UserDto userDto);




}
