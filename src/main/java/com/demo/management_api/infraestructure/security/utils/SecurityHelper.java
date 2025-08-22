package com.demo.management_api.infraestructure.security.utils;

import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.infraestructure.exceptions.custom.NotDataFoundException;
import com.demo.management_api.infraestructure.models.response.AuthResponse;
import com.demo.management_api.infraestructure.models.CustomUserDetails;
import com.demo.management_api.domain.repository.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityHelper {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public List<SimpleGrantedAuthority> createAuthorityList(UserEntity user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Se obtiene la lista de roles por el 'id' del usuario y se concatena
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));
        // Se obtiene la lista de autoridades de cada rol
        user.getRoles().stream()
                .flatMap(r -> r.getAuthorities().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getAuthority())));

        return authorities;
    }

    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotDataFoundException("Error to find user with email: %s".formatted(email)));
    }

    public AuthResponse buildAuthResponse(UserEntity user, String refreshToken) {
        log.info("User: {}", user.getUserDetail().getName());
        List<SimpleGrantedAuthority> authorities = createAuthorityList(user);
        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .userId(user.getId())
                .username(user.getUserDetail().getName())
                .authorities(authorities)
                .build();
        Authentication auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, authorities);
        return AuthResponse.builder()
                .message("Login successfully")
                .token(jwtUtils.createToken(auth))
                .refreshToken(refreshToken)
                .build();
    }

}
