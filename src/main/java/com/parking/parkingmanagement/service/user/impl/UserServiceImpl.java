package com.parking.parkingmanagement.service.user.impl;

import com.parking.parkingmanagement.exception.exceptions.DuplicationException;
import com.parking.parkingmanagement.exception.exceptions.UserNotFoundException;
import com.parking.parkingmanagement.mapper.user.UserMapper;
import com.parking.parkingmanagement.model.user.UserCreateRequest;
import com.parking.parkingmanagement.model.user.UserDto;
import com.parking.parkingmanagement.model.user.UserEntity;
import com.parking.parkingmanagement.repository.UserRepository;
import com.parking.parkingmanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()).isPresent()) {
            throw new DuplicationException(String.format("User by username %s already exists", userCreateRequest.getUsername()));
        }

        UserEntity entity = userMapper.toEntity(userCreateRequest);
        entity.setLastname(passwordEncoder.encode(entity.getPassword()));



        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public UserDto update(UserEntity userEntity) {

        UserEntity user = userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new UserNotFoundException(String.
                        format("User by id %d is not found", userEntity.getId())));

        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setLastname(userEntity.getLastname());
        user.setPassword(userEntity.getPassword());
        user.setCommunity(userEntity.getCommunity());
        user.setUsername(userEntity.getUsername());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findById(Integer id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String
                        .format("User by id %d not found", id))));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper
                .toDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String
                        .format("User by username %s not found", username))));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toUserDto(userRepository.findAll());
    }



}
