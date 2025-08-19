package com.demo.management_api.domain.repository;

import com.demo.management_api.domain.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Integer> {
}
