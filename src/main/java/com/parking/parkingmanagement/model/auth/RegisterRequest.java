package com.parking.parkingmanagement.model.auth;

import com.parking.parkingmanagement.constants.Community;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;

    private String lastname;

    private String username;

    private String password;

    private Community community;


}
