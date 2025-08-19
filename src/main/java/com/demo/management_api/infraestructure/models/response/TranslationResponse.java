package com.demo.management_api.infraestructure.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationResponse {
    private Integer educationId;
    private List<FieldResponse> fields;
}
