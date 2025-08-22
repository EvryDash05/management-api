package com.demo.management_api.commons.constants;

public abstract class ApiConstants {

    // Authorization
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    public static final String USER = "USER";
    public static final String SYSTEM = "SYSTEM";

    // Auth endpoint
    public static final String AUTH_PREFIX_ENDPOINT = "${api.uri.domain.auth}";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String REGISTER_ENDPOINT = "/register";
    public static final String REFRESH_TOKEN_ENDPOINT = "/refresh-token";
    public static final String SEND_EMAIL_PASSWORD_RECOVERY_ENDPOINT = "/send-email-recovery-password";
    public static final String RESET_PASSWORD_ENDPOINT = "/auth";

    // Educations endpoints
    public static final String REGISTER_EDUCATION = "/eduction/register";
    public static final String FIND_BY_EDUCATION_ID = "/education/{id}";
    public static final String GET_ALL_EDUCATION = "/education";


    // Test endpoint xd
    public static final String TEST_ENDPOINT = "/test";

    private ApiConstants() {}
}
