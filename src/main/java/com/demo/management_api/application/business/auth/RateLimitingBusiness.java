package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.RateLimitingService;
import com.demo.management_api.commons.constants.CacheConfigConstants;
import com.demo.management_api.infraestructure.exceptions.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimitingBusiness implements RateLimitingService {

    private final int MAX_REQUEST = 5;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void checkRateLimit(String ipAddress, String url) {
        String key = ipAddress.concat(url);
        Long count = this.redisTemplate.opsForValue().increment(key);

        if (count != null && count == 1) {
            redisTemplate.expire(key, Duration.ofMinutes(CacheConfigConstants.DURATION_RATE_LIMITING));
        }

        if (count != null && count == MAX_REQUEST) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Rate limit exceeded");
        }
    }


}
