package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by", updatable = false, nullable = false)
    protected String createdBy;

    /**
     * Fecha de creación.
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    protected Date createdDate;

    /**
     * Usuario que modifica el registro.
     */
    @LastModifiedBy
    @Column(name = "last_update_by", nullable = false)
    protected String lastModifiedBy;

    /**
     * Fecha que modifica.
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_at", nullable = false)
    protected Date lastModifiedDate;
}
