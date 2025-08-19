package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
// @Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @Table(name = "role_authority", schema = "db_management")
public class RoleAuthority {
    @EmbeddedId
    private RoleAuthorityId id;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @MapsId("authorityId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "authority_id", nullable = false)
    private AuthorityEntity authority;

}