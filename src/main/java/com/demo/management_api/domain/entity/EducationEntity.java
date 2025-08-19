package com.demo.management_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "educations", schema = "db_management")
public class EducationEntity extends AuditableEntity {
    @Id
    @Column(name = "education_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;
}