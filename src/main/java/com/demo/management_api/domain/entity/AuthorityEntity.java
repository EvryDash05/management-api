package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities", schema = "db_management")
public class AuthorityEntity extends AuditableEntity {
    @Id
    @Column(name = "authority_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<RoleEntity> roleEntities;
}