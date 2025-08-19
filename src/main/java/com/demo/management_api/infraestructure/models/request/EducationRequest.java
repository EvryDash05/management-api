package com.demo.management_api.infraestructure.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequest {
    private Integer orderNumber;
    private TranslationRequest translation;
}
