package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "calendar_conference", schema = "db_management")
public class CalendarConferenceEntity extends AuditableEntity {
    @Id
    @Column(name = "calendar_conference_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "calendar_id", nullable = false)
    private CalendarEntity calendar;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "teacher_id", nullable = false)
    private UserEntity teacher;

    @NotNull
    @Column(name = "place", nullable = false)
    private String place;

    @Size(max = 11)
    @NotNull
    @Column(name = "type", nullable = false, length = 11)
    private String type;

}