package com.demo.management_api.domain.repository.auth;

import com.demo.management_api.domain.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Integer> {
    Optional<UserDetailEntity> findByName(String name);
    Optional<UserDetailEntity> findByUserId(Integer userId);
}
