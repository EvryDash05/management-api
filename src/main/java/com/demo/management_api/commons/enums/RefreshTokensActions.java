package com.demo.management_api.commons.enums;

import lombok.Getter;

@Getter
public enum RefreshTokensActions {
    CREATE("CREATE"),
    REVOKE("REVOKE");

    private final String code;

    RefreshTokensActions(String code) {
        this.code = code;
    }
}
