package com.demo.management_api.application.services;

import com.demo.management_api.domain.entity.EducationEntity;
import com.demo.management_api.infraestructure.models.request.TranslationRequest;

public interface TranslationService {
    void registerTranslation(TranslationRequest request, Integer newEducationId);
}
