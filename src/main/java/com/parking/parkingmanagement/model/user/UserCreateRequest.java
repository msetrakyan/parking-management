package com.parking.parkingmanagement.model.user;


import com.parking.parkingmanagement.constants.Community;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {

    private String name;

    private String lastname;

    private String username;

    private String password;

    private Community community;



}
