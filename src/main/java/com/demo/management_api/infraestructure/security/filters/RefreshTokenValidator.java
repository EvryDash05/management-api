package com.demo.management_api.infraestructure.security.filters;

import com.demo.management_api.application.services.TokenBlackListService;
import com.demo.management_api.domain.entity.RefreshTokenEntity;
import com.demo.management_api.domain.repository.auth.RefreshTokenRepository;
import com.demo.management_api.infraestructure.exceptions.custom.BusinessException;
import com.demo.management_api.infraestructure.models.request.RefreshTokenContext;
import com.demo.management_api.infraestructure.security.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Filter responsible for validating incoming refresh tokens.
 * This filter is only applied to the refresh-token endpoint,
 * not to the normal request flow that uses access tokens.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshTokenValidator implements Filter {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenContext refreshTokenContext;
    private final TokenBlackListService tokenBlackListService;
    private final JwtUtils jwtUtils;

    /**
     * Core filter method executed for every matching request.
     * - Extracts refresh token from the header
     * - Validates it (existence, not revoked, not expired)
     * - Stores context info (IP, browser, token entity) for later use in the business layer
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        log.info("Refresh token filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String clientIp = request.getRemoteAddr();
        String refreshToken = request.getHeader("Refresh-token");
        String browser = request.getHeader("User-Agent");

        if (refreshToken != null) {
            log.info("Refresh token from filter: {}", refreshToken);
            RefreshTokenEntity validRefreshToken = this.validateRefreshToken(refreshToken);
            createRequestContext(validRefreshToken, clientIp, browser);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Validates that the refresh token exists in DB, is not revoked, and is not expired.
     *
     * @param refreshToken raw refresh token (string from client)
     * @return RefreshTokenEntity if valid
     * @throws RuntimeException if token is missing, revoked, or expired
     */
    private RefreshTokenEntity validateRefreshToken(String refreshToken) {
        try {
            String hashedToken = jwtUtils.buildHashedRefreshToken(refreshToken);
            log.info("Hashed token from filter: {}", hashedToken);
            boolean isTokenExists = this.tokenBlackListService.isTokenBlackListed(hashedToken);

            if (isTokenExists)
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Token revoked or expired");

            RefreshTokenEntity findToken = this.refreshTokenRepository.findByTokenHashAndRevokedFalse(hashedToken)
                    .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "Error to find token or revoked"));
            this.jwtUtils.validateNotExpired(findToken.getExpiresAt());

            return findToken;
        } catch (Exception e) {
            log.error("Error to validate refresh token: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Stores request-specific metadata into a shared context (Request scoped bean).
     * This makes it possible to access the validated refresh token and client info later in business logic.
     */
    private void createRequestContext(RefreshTokenEntity refreshToken, String ipAddress, String browser) {
        refreshTokenContext.setIpAddress(ipAddress);
        refreshTokenContext.setBrowser(browser);
        refreshTokenContext.setRefreshToken(refreshToken);
    }

}
