package com.demo.management_api.commons.constants;

public abstract class ApiConstants {

    public static final String BASE_API_URL = "/api/v1/";

    // Authorization
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    public static final String USER = "USER";
    public static final String SYSTEM = "SYSTEM";

    // Educations endpoints
    public static final String REGISTER_EDUCATION = "/eduction/register";
    public static final String FIND_BY_EDUCATION_ID = "/education/{id}";
    public static final String GET_ALL_EDUCATION = "/education";


    private ApiConstants() {}
}
