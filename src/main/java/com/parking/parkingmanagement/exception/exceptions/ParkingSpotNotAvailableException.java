package com.parking.parkingmanagement.exception.exceptions;

public class ParkingSpotNotAvailableException extends RuntimeException{
    public ParkingSpotNotAvailableException(String message) {
        super(message);
    }
}
