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
@Table(name = "districts", schema = "db_management")
public class DistrictEntity extends AuditableEntity {
    @Id
    @Size(max = 15)
    @Column(name = "district_id", nullable = false, length = 15)
    private String districtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "province_id")
    private ProvinceEntity provinceEntity;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
}