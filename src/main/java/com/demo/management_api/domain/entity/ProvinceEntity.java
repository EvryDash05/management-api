package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provinces", schema = "db_management")
public class ProvinceEntity extends AuditableEntity {
    @Id
    @Size(max = 15)
    @Column(name = "province_id", nullable = false, length = 15)
    private String provinceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

}