package com.demo.management_api.infraestructure.models.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private String userDetailId;
    private String districtName;
    private String email;
    private String lastName;
}
