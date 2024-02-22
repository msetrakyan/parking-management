package com.parking.parkingmanagement.service.user;



import com.parking.parkingmanagement.model.user.UserCreateRequest;
import com.parking.parkingmanagement.model.user.UserDto;
import com.parking.parkingmanagement.model.user.UserEntity;

import java.util.List;

public interface UserService {

    UserDto create(UserCreateRequest userCreateRequest);

    void delete(Integer id);

    UserDto update(UserEntity userEntity);

    UserDto findById(Integer id);

    UserDto findByUsername(String username);

    List<UserDto> findAll();






}