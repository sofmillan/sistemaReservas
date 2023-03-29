package com.example.workshopLHotelAshir.exceptions;

public class IncorrectFormatException extends RuntimeException{

    public IncorrectFormatException(){}
    public IncorrectFormatException(String messageError){
        super(messageError);
    }

}
