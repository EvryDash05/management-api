package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.AuthService;
import com.demo.management_api.application.services.RefreshTokenService;
import com.demo.management_api.application.services.UserDetailService;
import com.demo.management_api.application.services.UserService;
import com.demo.management_api.commons.enums.RefreshTokensCreatedBy;
import com.demo.management_api.commons.enums.UserRoles;
import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.domain.repository.auth.UserRepository;
import com.demo.management_api.infraestructure.exceptions.custom.NotDataFoundException;
import com.demo.management_api.infraestructure.models.request.AuthUserRegisterRequest;
import com.demo.management_api.infraestructure.models.request.records.AuthLoginRequest;
import com.demo.management_api.infraestructure.models.response.AuthResponse;
import com.demo.management_api.infraestructure.security.utils.SecurityHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthBusiness implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsBusiness userDetailsBusiness;
    private final UserRepository userRepository;
    private final SecurityHelper securityHelper;
    private final UserDetailService userDetailService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        Authentication authentication = this.authenticate(request.email(), request.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity findUser = this.getUserByEmail(request.email());
        String newRefreshToken = this.issueNewTokens(findUser);

        return this.securityHelper.buildAuthResponse(findUser, newRefreshToken);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = this.userDetailsBusiness.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Transactional
    public String createUser(AuthUserRegisterRequest request) {
        createUserAndRoles(request, UserRoles.USER.getCode());
        return "User created!!";
    }

    private UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotDataFoundException("User not found"));
    }

    private void createUserAndRoles(AuthUserRegisterRequest request, String role) {
        UserEntity newUser = this.userService.registerUser(request, role);
        this.userDetailService.createUserDetail(request, newUser);
    }

    private String issueNewTokens(UserEntity user) {
        // Revoke any existing refresh token for this user (enforces one active token per user)
        this.refreshTokenService.revokeToken(user.getId());
        // Generate a new refresh token and persist it
        return this.refreshTokenService.saveRefreshToken(user, RefreshTokensCreatedBy.USER_USER.getCode());
    }

}
