package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.UserService;
import com.demo.management_api.domain.entity.RoleEntity;
import com.demo.management_api.domain.entity.UserDetailEntity;
import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.domain.repository.auth.RoleRepository;
import com.demo.management_api.domain.repository.auth.UserDetailRepository;
import com.demo.management_api.domain.repository.auth.UserRepository;
import com.demo.management_api.infraestructure.models.request.AuthUserRegisterRequest;
import com.demo.management_api.infraestructure.models.response.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserBusiness implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailRepository userDetailRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsResponse findUserData(Integer userId){
        UserDetailEntity findUserDetailEntity = this.userDetailRepository
                .findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toUserDetailsResponse(findUserDetailEntity);
    }

    @Override
    public UserEntity registerUser(AuthUserRegisterRequest request, String role) {
        RoleEntity findRoleEntity = this.roleRepository.findByName(role);
        return this.userRepository.save(UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>(Collections.singleton(findRoleEntity)))
                .status(false)
                .build());
    }

    private UserDetailsResponse toUserDetailsResponse(UserDetailEntity entity){
        return new ModelMapper().map(entity, UserDetailsResponse.class);
    }

}
