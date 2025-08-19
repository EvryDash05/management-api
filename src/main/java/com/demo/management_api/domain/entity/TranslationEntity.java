package com.demo.management_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "translations", schema = "db_management")
public class TranslationEntity extends AuditableEntity {
    @Id
    @Column(name = "translation_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    @Size(max = 5)
    @NotNull
    @Column(name = "language", nullable = false, length = 5)
    private String language;

    @Size(max = 50)
    @NotNull
    @Column(name = "field", nullable = false, length = 50)
    private String field;

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    @Size(max = 50)
    @NotNull
    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType;
}