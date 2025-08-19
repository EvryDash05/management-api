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
@Table(name = "password_reset_token_audit", schema = "db_management")
public class PasswordResetTokenAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "token_id", nullable = false)
    private PasswordResetTokenEntity token;

    @Size(max = 255)
    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Size(max = 255)
    @Column(name = "last_update_by")
    private String lastUpdateBy;

    @Column(name = "last_update_date")
    private Instant lastUpdateDate;

    @Size(max = 255)
    @Column(name = "revoked_by")
    private String revokedBy;

    @Size(max = 45)
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;
}
