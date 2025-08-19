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
@Table(name = "learning_sessions", schema = "db_management")
public class LearningSessionEntity extends AuditableEntity {
    @Id
    @Column(name = "learning_session_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Size(max = 15)
    @NotNull
    @Column(name = "role", nullable = false, length = 15)
    private String role;

    @Size(max = 45)
    @NotNull
    @Column(name = "ip_address", nullable = false, length = 45)
    private String ipAddress;

    @Size(max = 15)
    @NotNull
    @Column(name = "device", nullable = false, length = 15)
    private String device;

    @NotNull
    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

    @Size(max = 15)
    @NotNull
    @Column(name = "latitude", nullable = false, length = 15)
    private String latitude;

    @Size(max = 15)
    @NotNull
    @Column(name = "longitude", nullable = false, length = 15)
    private String longitude;

    @NotNull
    @Column(name = "login_at", nullable = false)
    private Instant loginAt;

    @NotNull
    @Column(name = "logout_at", nullable = false)
    private Instant logoutAt;

    @NotNull
    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

}