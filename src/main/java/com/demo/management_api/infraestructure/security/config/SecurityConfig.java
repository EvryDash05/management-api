package com.demo.management_api.infraestructure.security.config;

import com.demo.management_api.application.business.auth.UserDetailsBusiness;
import com.demo.management_api.infraestructure.security.constants.UrlConstants;
import com.demo.management_api.infraestructure.security.filters.JwtTokenValidator;
import com.demo.management_api.infraestructure.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/*
 * Main Spring Security configuration.
 *
 * Defines two different security filter chains:
 * 1. A dedicated chain for the refresh-token endpoint.
 * 2. The main chain for all other endpoints.
 *
 * This separation ensures that refresh token requests are handled
 * differently from normal requests that require an access token.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain mainSecurityFilter(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(PUBLIC_URLS).permitAll();
                    http.requestMatchers(HttpMethod.POST, AUTH_URLS).permitAll();
                    http.requestMatchers(HttpMethod.PATCH, "/api/v1/auth/reset-password").permitAll();
                    http.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationManager) throws Exception {
        return authenticationManager.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsBusiness userDetailsBusiness) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsBusiness);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(DEV_URLS));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        return request -> configuration;
//    }

    private static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
    };

    private static final String[] AUTH_URLS = {
            "/api/v1/auth/login",
            "/api/v1/auth/refresh-token",
            "/api/v1/auth/test",
    };

    /* private static final String[] AUTH_URLS = {
            "/api/v1/auth/register",
            "/api/v1/auth/send-email-recovery-password",
            "/api/v1/auth/reset-password",
    }; */

    private static final String[] DEV_URLS = {
            "http://localhost:5173"
    };

}
