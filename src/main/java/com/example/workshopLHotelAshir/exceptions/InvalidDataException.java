package com.example.workshopLHotelAshir.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
    }

    public InvalidDataException(String messageError){
        super(messageError);
    }

}
