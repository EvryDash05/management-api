package com.demo.management_api.infraestructure.configs;

import com.demo.management_api.infraestructure.security.filters.RefreshTokenValidator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersRegistration {

    @Bean
    public FilterRegistrationBean<RefreshTokenValidator> refreshTokenFilterRegistration(RefreshTokenValidator filter) {
        FilterRegistrationBean<RefreshTokenValidator> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/api/v1/auth/refresh-token");
        return registration;
    }

}
