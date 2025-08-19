package com.demo.management_api.commons.enums;

import lombok.Getter;

@Getter
public enum RefreshTokensCreatedBy {
    USER_SYSTEM("SYSTEM"),
    USER_USER("USER"),
    USER_ADMIN("ADMIN");

    private final String code;

    RefreshTokensCreatedBy(String code) {
        this.code = code;
    }

}
