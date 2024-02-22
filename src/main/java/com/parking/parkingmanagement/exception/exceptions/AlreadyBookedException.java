package com.parking.parkingmanagement.exception.exceptions;

public class AlreadyBookedException extends RuntimeException{
    public AlreadyBookedException(String message) {
        super(message);
    }
}
