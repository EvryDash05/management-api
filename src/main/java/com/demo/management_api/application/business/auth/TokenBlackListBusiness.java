package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.TokenBlackListService;
import com.demo.management_api.domain.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenBlackListBusiness implements TokenBlackListService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveOnBlackListToken(RefreshTokenEntity token) {
        long ttlSeconds = Duration.between(Instant.now(), token.getExpiresAt()).toSeconds();

        if (ttlSeconds > 0) {
            stringRedisTemplate.opsForValue().set("blacklist:" + token.getTokenHash(), "revoked", ttlSeconds, TimeUnit.SECONDS);
            log.info("Token in blacklist...");
        }

    }

    @Override
    public boolean isTokenBlackListed(String token) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey("blacklist:" + token));
    }

}
