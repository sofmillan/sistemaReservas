package com.example.workshopLHotelAshir.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){}
    public DataNotFoundException(String messageError){
        super(messageError);
    }
}
