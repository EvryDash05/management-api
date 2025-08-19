package com.demo.management_api.application.services;

import com.demo.management_api.infraestructure.models.response.AuthResponse;
import com.demo.management_api.infraestructure.models.request.records.AuthLoginRequest;

public interface AuthService {
    AuthResponse login(AuthLoginRequest request);
}
