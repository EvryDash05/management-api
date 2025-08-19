package com.demo.management_api.commons.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("ADMIN"),
    USER("USER"),
    TEACHER("TEACHER"),
    MARKETING("MARKETING");

    private final String code;

    UserRoles(String code) {
        this.code = code;
    }

}
