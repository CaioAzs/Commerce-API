package com.azscaio.webproject.services.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException (String message){
        super(message);
    }
}
