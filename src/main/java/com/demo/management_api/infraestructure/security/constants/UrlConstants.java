package com.demo.management_api.infraestructure.security.constants;

public class UrlConstants {

    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/auth/**",
    };

    public static final String[] AUTH_URLS = {
            "/auth/register",
            "/auth/login",
            "/auth/send-email-recovery-password",
            "/auth/reset-password",
            "/auth/refresh-token",
            "/auth/test"
    };

    public static final String[] DEV_URLS = {
            "http://localhost:5173"
    };

    private UrlConstants() {
    }
}
