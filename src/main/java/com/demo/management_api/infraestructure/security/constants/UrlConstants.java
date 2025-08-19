package com.demo.management_api.infraestructure.security.constants;

public class UrlConstants {

    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/auth/**",
    };

    public static String[] authUrls() {
        return new String[]{
                "/api/v1/auth/register",
                "/api/v1/auth/login",
                "/api/v1/auth/send-email-recovery-password",
                "/api/v1/auth/reset-password",
                "/api/v1/auth/refresh-token",
                "/api/v1/auth/test"
        };
    }

    public static final String[] DEV_URLS = {
            "http://localhost:5173"
    };

    private UrlConstants() {
    }
}
