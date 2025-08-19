package com.demo.management_api.infraestructure.exceptions.custom;

public class NotDataFoundException extends RuntimeException {

    public NotDataFoundException() {
        super();
    }

    public NotDataFoundException(String message) {
        super(message);
    }
}
