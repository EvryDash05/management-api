package com.demo.management_api.infraestructure.models.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserRegisterRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String districtId;
}
