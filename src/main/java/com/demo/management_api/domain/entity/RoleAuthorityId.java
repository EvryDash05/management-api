package com.demo.management_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuthorityId implements java.io.Serializable {
    private static final long serialVersionUID = -1052299905668999192L;
    @NotNull
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @NotNull
    @Column(name = "authority_id", nullable = false)
    private Integer authorityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleAuthorityId entity = (RoleAuthorityId) o;
        return Objects.equals(this.authorityId, entity.authorityId) &&
                Objects.equals(this.roleId, entity.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId, roleId);
    }

}