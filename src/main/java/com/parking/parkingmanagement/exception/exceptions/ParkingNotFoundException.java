package com.parking.parkingmanagement.exception.exceptions;

public class ParkingNotFoundException extends RuntimeException{
    public ParkingNotFoundException(String message) {
        super(message);
    }
}
