package com.demo.management_api.application.services;

import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.infraestructure.models.request.AuthUserRegisterRequest;

public interface UserDetailService {
    void createUserDetail(AuthUserRegisterRequest request, UserEntity user);
}
