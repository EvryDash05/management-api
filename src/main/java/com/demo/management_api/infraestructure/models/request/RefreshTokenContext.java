package com.demo.management_api.infraestructure.models.request;

import com.demo.management_api.domain.entity.RefreshTokenEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RefreshTokenContext {
    private RefreshTokenEntity refreshToken;
    private String browser;
    private String ipAddress;
}

