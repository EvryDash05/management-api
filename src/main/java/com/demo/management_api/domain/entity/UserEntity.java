package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "db_management")
public class UserEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 250)
    @NotNull
    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetailEntity userDetail;

    @ManyToMany
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user")
    private Set<RefreshTokenEntity> refreshTokens;

    @OneToMany(mappedBy = "user")
    private Set<PasswordResetTokenEntity> passwordResetTokens;

}