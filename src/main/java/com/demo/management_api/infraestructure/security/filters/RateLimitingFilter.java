package com.demo.management_api.infraestructure.security.filters;

import com.demo.management_api.application.services.RateLimitingService;
import com.demo.management_api.infraestructure.models.IpAddressContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class RateLimitingFilter implements Filter {

    private final RateLimitingService rateLimitingService;
    private final IpAddressContext ipAddressContext;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        log.info("Rate limiting filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String ipAddress = request.getRemoteAddr();
        String url = request.getRequestURI();

        rateLimitingService.checkRateLimit(ipAddress, url);
        this.ipAddressContext.setIpAddress(ipAddress);

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
