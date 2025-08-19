package com.demo.management_api.application.business.auth;

import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.infraestructure.models.CustomUserDetails;
import com.demo.management_api.infraestructure.security.utils.SecurityHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsBusiness implements UserDetailsService {

    private final SecurityHelper securityHelper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity findUser = this.securityHelper.getUserByEmail(email);
        log.info("User find: {}", findUser.toString());
        return CustomUserDetails.builder()
                .userId(findUser.getId())
                .username(findUser.getUserDetail().getName())
                .password(findUser.getPassword())
                .authorities(securityHelper.createAuthorityList(findUser.getUserDetail().getUser()))
                .build();
    }

}