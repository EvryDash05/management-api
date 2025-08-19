package com.demo.management_api.domain.repository.auth;

import com.demo.management_api.domain.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByTokenHashAndRevokedFalse(String tokenHash);

    Optional<RefreshTokenEntity> findRefreshTokenByTokenHash(String tokenHash);

    Optional<RefreshTokenEntity> findRefreshTokenByRevokedFalseAndUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshTokenEntity rt WHERE rt.revoked = true")
    int deleteByRevokedTrue();
}
