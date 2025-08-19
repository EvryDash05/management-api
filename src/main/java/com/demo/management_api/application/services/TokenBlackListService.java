package com.demo.management_api.application.services;

import com.demo.management_api.domain.entity.RefreshTokenEntity;

public interface TokenBlackListService {
    void saveOnBlackListToken(RefreshTokenEntity revokeToken);
    boolean isTokenBlackListed(String token);
}
