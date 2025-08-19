package com.demo.management_api.application.services;

public interface RateLimitingService {
    void checkRateLimit(String ipAddress, String url);
}
