package com.demo.management_api.infraestructure.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryPasswordResponse {
    public String token;
    public Integer userId;
    public String ipAddress;
}
