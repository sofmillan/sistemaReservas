package com.example.workshopLHotelAshir.exceptions;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(){
    }
    public DataAlreadyExistsException(String messageError){
        super(messageError);
    }
}

