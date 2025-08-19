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
@Table(name = "learning_session_events", schema = "db_management")
public class LearningSessionEventEntity extends AuditableEntity {
    @Id
    @Column(name = "learning_session_event_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity sessionEntity;

    @Size(max = 45)
    @NotNull
    @Column(name = "event_type", nullable = false, length = 45)
    private String eventType;

    @Size(max = 50)
    @NotNull
    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    @NotNull
    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

}