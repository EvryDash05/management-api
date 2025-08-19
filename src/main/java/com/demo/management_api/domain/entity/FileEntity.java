package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "files", schema = "db_management")
public class FileEntity extends AuditableEntity {
    @Id
    @Column(name = "file_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    @NotNull
    @Column(name = "link", nullable = false)
    private String link;

    @Size(max = 50)
    @NotNull
    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;
}