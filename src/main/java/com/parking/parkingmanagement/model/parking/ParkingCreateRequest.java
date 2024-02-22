package com.parking.parkingmanagement.model.parking;

import com.parking.parkingmanagement.constants.Community;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingCreateRequest {


    private Integer parkingNumber;

    private Community community;

}
