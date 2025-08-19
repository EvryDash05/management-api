package com.demo.management_api.commons.constants;

public abstract class CacheConfigConstants {

    public static final String RECOVERY_PASSWORD_TOKENS = "recovery-password-tokens";
    public static final int DURATION_RECOVERY_PASSWORD_TOKENS = 15;

    public static final String RATE_LIMITING = "rate-limiting";
    public static final int DURATION_RATE_LIMITING = 1;

    private CacheConfigConstants() {}
}
