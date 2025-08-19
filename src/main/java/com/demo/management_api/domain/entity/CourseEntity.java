package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "courses", schema = "db_management")
public class CourseEntity extends AuditableEntity {
    @Id
    @Column(name = "course_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "modality_id", nullable = false)
    private ModalityEntity modalityEntity;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @NotNull
    @Column(name = "hours", nullable = false)
    private Integer hours;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

}