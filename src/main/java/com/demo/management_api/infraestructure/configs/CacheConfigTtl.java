package com.demo.management_api.infraestructure.configs;

import com.demo.management_api.commons.constants.CacheConfigConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheConfigTtl {

    @Bean
    public Map<String, Duration> cacheTtlMap(){
        Map<String, Duration> ttl = new HashMap<>();
        ttl.put(CacheConfigConstants.RECOVERY_PASSWORD_TOKENS, Duration.ofMinutes(CacheConfigConstants.DURATION_RECOVERY_PASSWORD_TOKENS));
        ttl.put(CacheConfigConstants.RATE_LIMITING, Duration.ofMinutes(CacheConfigConstants.DURATION_RATE_LIMITING));
        return ttl;
    }

}
