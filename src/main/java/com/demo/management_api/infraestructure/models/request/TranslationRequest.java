package com.demo.management_api.infraestructure.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequest {
    private int entityId;
    private String language;
    private String field;
    private String value;
    private String entityType;
}
