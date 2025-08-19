package com.demo.management_api.application.services;

import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.infraestructure.models.response.AuthResponse;

public interface RefreshTokenService {
    String saveRefreshToken(UserEntity user, String actionBy);
    AuthResponse createNewAccessToken();
    String revokeToken(Integer userId);
}
