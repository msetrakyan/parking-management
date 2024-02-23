package com.parking.parkingmanagement.service.authentication;

import com.parking.parkingmanagement.model.auth.AuthenticationRequest;
import com.parking.parkingmanagement.model.auth.AuthenticationResponse;
import com.parking.parkingmanagement.model.auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse login(AuthenticationRequest request);


}