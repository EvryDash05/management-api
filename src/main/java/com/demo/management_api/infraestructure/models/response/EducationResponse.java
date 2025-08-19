package com.demo.management_api.infraestructure.models.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class EducationResponse {
    private Integer educationId;
    private List<TranslationResponse> translations;
}
