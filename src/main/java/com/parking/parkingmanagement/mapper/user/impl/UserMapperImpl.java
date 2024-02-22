package com.parking.parkingmanagement.mapper.user.impl;


import com.parking.parkingmanagement.mapper.user.UserMapper;
import com.parking.parkingmanagement.model.user.UserCreateRequest;
import com.parking.parkingmanagement.model.user.UserDto;
import com.parking.parkingmanagement.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {



    @Override
    public UserDto toDto(UserEntity userEntity) {
        if(userEntity == null) {
            return null;
        }
        return UserDto.builder()
                .lastname(userEntity.getLastname())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .build();
    }

    @Override
    public UserEntity toEntity(UserCreateRequest userCreateRequest) {

        UserEntity user = new UserEntity();

        user.setPassword(userCreateRequest.getPassword());
        user.setName(userCreateRequest.getName());
        user.setLastname(userCreateRequest.getLastname());
        user.setCommunity(userCreateRequest.getCommunity());
        user.setUsername(userCreateRequest.getUsername());

        return user;
    }

    @Override
    public List<UserDto> toUserDto(List<UserEntity> userEntities) {
        return userEntities.stream().map(this::toDto).toList();
    }



}
