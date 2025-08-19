package com.demo.management_api.application.services;

import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.infraestructure.models.request.AuthUserRegisterRequest;
import com.demo.management_api.infraestructure.models.response.UserDetailsResponse;

public interface UserService {
    UserDetailsResponse findUserData(Integer userId);
    UserEntity registerUser(AuthUserRegisterRequest request, String role);
}
