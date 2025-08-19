package com.demo.management_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments", schema = "db_management")
public class DepartmentEntity extends AuditableEntity {
    @Id
    @Size(max = 15)
    @Column(name = "department_id", nullable = false, length = 15)
    private String departmentId;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
}