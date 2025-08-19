package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.RecoveryPasswordService;
import com.demo.management_api.commons.constants.CacheConfigConstants;
import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.domain.repository.auth.UserRepository;
import com.demo.management_api.infraestructure.exceptions.custom.BusinessException;
import com.demo.management_api.infraestructure.exceptions.custom.NotDataFoundException;
import com.demo.management_api.infraestructure.models.IpAddressContext;
import com.demo.management_api.infraestructure.models.request.ResetPasswordRequest;
import com.demo.management_api.infraestructure.models.request.records.SendRequestPasswordRequest;
import com.demo.management_api.infraestructure.models.response.RecoveryPasswordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecoveryPasswordBusiness implements RecoveryPasswordService {

    private final UserRepository userRepository;
    private final CacheManager cacheManager;
    private final PasswordEncoder passwordEncoder;
    private final IpAddressContext ipAddressContext;

    @Override
    @CachePut(value = CacheConfigConstants.RECOVERY_PASSWORD_TOKENS, key = "#result.token")
    public RecoveryPasswordResponse sendRequestRecoverPassword(SendRequestPasswordRequest request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "User not found"));

        return new RecoveryPasswordResponse(generateTokenRecoveryPassword(), user.getId(), this.ipAddressContext.getIpAddress());
    }

    @Override
    @CacheEvict(value = CacheConfigConstants.RECOVERY_PASSWORD_TOKENS, key = "#result")
    public String updatePassword(ResetPasswordRequest request, String token) {

        Cache cache = cacheManager.getCache(CacheConfigConstants.RECOVERY_PASSWORD_TOKENS);
        RecoveryPasswordResponse findCache = cache.get(token, RecoveryPasswordResponse.class);

        if (findCache == null) {
            throw new NotDataFoundException("Reset token password not found or expired");
        }

        if (!ipAddressContext.getIpAddress().equals(findCache.getIpAddress())) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "The request does not come from the same IP");
        }

        return this.applyPasswordChange(findCache, request.getNewPassword());
    }

    private String applyPasswordChange(RecoveryPasswordResponse cache, String newPassword) {
        UserEntity findUser = this.userRepository.findById(cache.userId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND.value(), "User not found"));

        findUser.setPassword(this.passwordEncoder.encode(newPassword));
        this.userRepository.save(findUser);

        log.info("Password updated successfully");
        return cache.token;
    }

    private String generateTokenRecoveryPassword() {
        return UUID.randomUUID().toString();
    }

}
