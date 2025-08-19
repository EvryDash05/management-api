package com.demo.management_api.infraestructure.schedules;

import com.demo.management_api.domain.repository.auth.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteRefreshTokensSchedule {

    private final RefreshTokenRepository refreshTokenRepository;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void deleteRefreshTokens() {
        log.info("Deleting refresh tokens");
        int count = this.refreshTokenRepository.deleteByRevokedTrue();
        log.info("Refresh tokens deleted: {}", count);
    }

}
