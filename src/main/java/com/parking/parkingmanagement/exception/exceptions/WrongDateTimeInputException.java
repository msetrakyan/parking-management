package com.parking.parkingmanagement.exception.exceptions;

public class WrongDateTimeInputException extends RuntimeException{
    public WrongDateTimeInputException(String message) {
        super(message);
    }
}
