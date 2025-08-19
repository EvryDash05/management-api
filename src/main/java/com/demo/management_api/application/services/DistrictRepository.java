package com.demo.management_api.application.services;

import com.demo.management_api.domain.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, String> {
}
