package com.demo.management_api.domain.repository.auth;

import com.demo.management_api.domain.entity.RefreshTokenAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenAuditRepository extends JpaRepository<RefreshTokenAuditEntity, Integer> {
}
