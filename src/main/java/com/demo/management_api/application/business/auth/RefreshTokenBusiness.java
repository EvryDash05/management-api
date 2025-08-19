package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.RefreshTokenService;
import com.demo.management_api.application.services.TokenBlackListService;
import com.demo.management_api.commons.constants.ApiConstants;
import com.demo.management_api.commons.enums.RefreshTokensActions;
import com.demo.management_api.domain.entity.RefreshTokenAuditEntity;
import com.demo.management_api.domain.entity.RefreshTokenEntity;
import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.domain.repository.auth.RefreshTokenAuditRepository;
import com.demo.management_api.domain.repository.auth.RefreshTokenRepository;
import com.demo.management_api.infraestructure.exceptions.custom.BusinessException;
import com.demo.management_api.infraestructure.models.request.RefreshTokenContext;
import com.demo.management_api.infraestructure.models.response.AuthResponse;
import com.demo.management_api.infraestructure.security.utils.JwtUtils;
import com.demo.management_api.infraestructure.security.utils.SecurityHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Business layer for managing refresh tokens.
 * <p>
 * Responsibilities:
 * - Create and persist new refresh tokens
 * - Revoke existing refresh tokens
 * - Audit token lifecycle events (create, revoke)
 * - Provide new access/refresh token pairs when requested
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenBusiness implements RefreshTokenService {

    private final JwtUtils jwtUtils;
    private final RefreshTokenAuditRepository refreshTokenAuditRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SecurityHelper securityHelper;
    private final RefreshTokenContext refreshTokenContext;
    private final TokenBlackListService tokenBlackListService;

    /**
     * Creates a new refresh token for the given user.
     * - Generates a raw JWT refresh token
     * - Hashes it for DB storage (security measure: raw token is never stored)
     * - Saves the token in the DB
     * - Saves an audit entry for traceability
     *
     * @param user     user for whom the refresh token is generated
     * @param actionBy the actor (system or user) who triggered this action
     * @return the raw refresh token (to be sent to the client)
     */
    @Override
    public String saveRefreshToken(UserEntity user, String actionBy) {
        try {
            log.info("Execute saveRefreshToken...");
            String refreshToken = jwtUtils.createRefreshToken(user.getId());
            String hashedToken = jwtUtils.buildHashedRefreshToken(refreshToken);

            log.info("Hashed token: {}", hashedToken);
            log.info("Refresh token: {}", refreshToken);

            RefreshTokenEntity newRefreshToken = this.saveRefreshToken(hashedToken, user);
            this.saveRefreshTokenAudit(newRefreshToken, actionBy, RefreshTokensActions.CREATE.getCode());

            log.info("New refresh token created...");
            return refreshToken;
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error to create token");
        }
    }

    /**
     * Revokes the current refresh token and issues a new access/refresh token pair.
     * <p>
     * Flow:
     * 1. Revoke old token (mark as revoked in DB, add audit record).
     * 2. Create and save a new refresh token for the same user.
     * 3. Build an AuthResponse containing a new access token and refresh token.
     */
    @Override
    @Transactional
    public AuthResponse createNewAccessToken() {
        log.info("Execute create new access token method...");
        try {
            this.revokeToken(refreshTokenContext.getRefreshToken().getUser().getId());
            String newRefreshToken = this.saveRefreshToken(refreshTokenContext.getRefreshToken().getUser(), ApiConstants.SYSTEM);

            return this.securityHelper.buildAuthResponse(refreshTokenContext.getRefreshToken().getUser(), newRefreshToken);
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error to create new tokens: " + e.getMessage());
        }
    }

    /**
     * Revokes an active refresh token for a given user.
     * - Marks token as revoked in DB
     * - Records an audit entry (IP, browser, who revoked it)
     *
     * @param userId ID of the user whose refresh token is revoked
     * @return confirmation message
     */
    @Override
    public String revokeToken(Integer userId) {
        this.refreshTokenRepository
                .findRefreshTokenByRevokedFalseAndUserId(userId)
                .ifPresent(refreshTokenEntity -> {
                    refreshTokenEntity.setRevoked(true);
                    refreshTokenEntity.setRevokedAt(Instant.now());
                    refreshTokenEntity.setRevokedBy(ApiConstants.SYSTEM);
                    RefreshTokenEntity updatedRefreshToken = this.refreshTokenRepository.save(refreshTokenEntity);

                    this.saveRefreshTokenAudit(updatedRefreshToken, ApiConstants.SYSTEM, RefreshTokensActions.REVOKE.getCode());

                    // Save revoked token on Redis cache
                    this.tokenBlackListService.saveOnBlackListToken(updatedRefreshToken);
                    log.info("Token revoked...");
                });

        return "Refresh token revoked (if existed)";
    }

    private RefreshTokenEntity saveRefreshToken(String hashedToken, UserEntity user) {
        return this.refreshTokenRepository.save(RefreshTokenEntity.builder()
                .tokenHash(hashedToken)
                .user(user)
                .createdAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .createdBy(ApiConstants.SYSTEM)
                .revoked(false)
                .build()
        );
    }

    private void saveRefreshTokenAudit(RefreshTokenEntity refreshToken, String actionBy, String actionType) {
        this.refreshTokenAuditRepository.save(RefreshTokenAuditEntity.builder()
                .token(refreshToken)
                .actionAt(Instant.now())
                .actionBy(actionBy)
                .actionType(actionType)
                .userAgent(refreshTokenContext.getBrowser())
                .ipAddress(refreshTokenContext.getIpAddress())
                .build()
        );
    }

}
