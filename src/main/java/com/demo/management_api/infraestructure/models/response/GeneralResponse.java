package com.demo.management_api.infraestructure.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}
