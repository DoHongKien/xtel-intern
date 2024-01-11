package com.springredis.exception;

public class IdNotFoundException extends Exception{
    public IdNotFoundException(String message) {
        super(message);
    }
}
