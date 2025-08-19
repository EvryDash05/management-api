package com.demo.management_api.infraestructure.exceptions.custom;

import java.util.List;

public class BadRequestException extends RuntimeException {

    private final List<String> badFields;

    public BadRequestException(String message, List<String> badFields) {
        super(message);
        this.badFields = badFields;
    }

    public List<String> getBadFields() {
        return badFields;
    }

}
