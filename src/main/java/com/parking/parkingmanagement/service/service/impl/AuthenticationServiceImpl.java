package com.parking.parkingmanagement.service.service.impl;

import com.parking.parkingmanagement.config.security.jwt.JwtService;
import com.parking.parkingmanagement.constants.Role;
import com.parking.parkingmanagement.exception.exceptions.DuplicationException;
import com.parking.parkingmanagement.model.auth.AuthenticationRequest;
import com.parking.parkingmanagement.model.auth.AuthenticationResponse;
import com.parking.parkingmanagement.model.auth.RegisterRequest;
import com.parking.parkingmanagement.model.user.UserEntity;
import com.parking.parkingmanagement.repository.UserRepository;
import com.parking.parkingmanagement.service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {

        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new DuplicationException("Username already exists");
        }

        UserEntity user = new UserEntity();

        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setLastname(registerRequest.getLastname());
        user.setUsername(registerRequest.getUsername());
        user.setCommunity(registerRequest.getCommunity());
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }



    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserEntity userEntity = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String jwtToken = jwtService.generateToken(userEntity);

        return new AuthenticationResponse(jwtToken);
    }




}
