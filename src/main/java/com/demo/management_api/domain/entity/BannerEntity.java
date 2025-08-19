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
@Table(name = "banners", schema = "db_management")
public class BannerEntity extends AuditableEntity {
    @Id
    @Column(name = "banner_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "redirect_link", nullable = false)
    private String redirectLink;

    @NotNull
    @Column(name = "link", nullable = false)
    private String link;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @Size(max = 25)
    @NotNull
    @Column(name = "type", nullable = false, length = 25)
    private String type;
}