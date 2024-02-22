package com.parking.parkingmanagement.model.booking;


import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    private Integer parkingNumber;

    private LocalDateTime bookedFrom;

    private LocalDateTime bookedTo;

    private String bookedBy;

    private Community community;



}
