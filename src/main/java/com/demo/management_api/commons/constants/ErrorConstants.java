package com.demo.management_api.commons.constants;

public class ErrorConstants {

    /** Generic server error code. */
    public static final Integer GENERIC_ERROR_CODE = 500;

    /**
     * Generic server error message.
     */
    public static final String GENERIC_ERROR_MESSAGE = "An unknown error occurred";

    /**
     * Bad request error code.
     */
    public static final Integer BAD_REQUEST_CODE = 400;

    /**
     * Bad request error message.
     */
    public static final String BAD_REQUEST_MESSAGE = "The request is invalid or cannot be processed";

    /**
     * Conflict error code (e.g., data already exists).
     */
    public static final Integer CONFLICT_CODE = 409;

    /**
     * Conflict error message.
     */
    public static final String CONFLICT_MESSAGE = "The request could not be completed due to a conflict with the current state of the resource";

    /**
     * Error code for validation failures.
     */
    public static final Integer VALIDATION_ERROR_CODE = 422;

    /**
     * Error message for validation failures.
     */
    public static final String VALIDATION_ERROR_MESSAGE = "Validation failed for one or more fields";

    /**
     * Error code for record already exists.
     */
    public static final Integer RECORD_EXISTS_CODE = 409;

    /**
     * Error message for record already exists.
     */
    public static final String RECORD_EXISTS_MESSAGE = "The record already exists";

    /**
     * Error code for record not found.
     */
    public static final Integer RECORD_NOT_FOUND_CODE = 404;

    /**
     * Error message for record not found.
     */
    public static final String RECORD_NOT_FOUND_MESSAGE = "The record was not found";

    /**
     * Error message for database-related errors.
     */
    public static final String DATABASE_ERROR_MESSAGE = "A database error occurred";

    private ErrorConstants() {}
}
