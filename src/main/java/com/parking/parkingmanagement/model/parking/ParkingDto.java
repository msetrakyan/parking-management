package com.parking.parkingmanagement.model.parking;


import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.user.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingDto {


    private Integer parkingNumber;

    private Community community;

    private UserDto userDto;

}