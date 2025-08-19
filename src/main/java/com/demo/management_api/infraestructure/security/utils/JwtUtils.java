package com.demo.management_api.infraestructure.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.management_api.infraestructure.models.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${security.jwt.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    @Value("${security.jwt.access.expiration}")
    private Long accessExpiration;

    @Value("${security.jwt.hashed.secret}")
    private String hashedSecretKey;

    public String createToken(Authentication auth) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        String username = ((CustomUserDetails) auth.getPrincipal()).getUsername();
        Integer userId = ((CustomUserDetails) auth.getPrincipal()).getUserId();
        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessExpiration))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            return jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new JWTVerificationException("Token invalid, not authorized");
        }
    }

    public String createRefreshToken(Integer userId) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        return JWT.create()
                .withSubject(userId.toString())
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public String buildHashedRefreshToken(String rawRefreshToken) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] keyBytes = Base64.getDecoder().decode(hashedSecretKey);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");
        mac.init(key);
        byte[] hashBytes = mac.doFinal(rawRefreshToken.getBytes(StandardCharsets.UTF_8));
        log.info("Secret key (base64): {}", Base64.getEncoder().encodeToString(key.getEncoded()));
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public void validateNotExpired(Instant expirateDate) {
        if (expirateDate.isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> returnClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }

}
