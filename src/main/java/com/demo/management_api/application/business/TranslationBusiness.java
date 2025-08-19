package com.demo.management_api.application.business;

import com.demo.management_api.application.services.TranslationService;
import com.demo.management_api.domain.entity.TranslationEntity;
import com.demo.management_api.domain.repository.TranslationRepository;
import com.demo.management_api.infraestructure.models.request.TranslationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslationBusiness implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    @Transactional
    public void registerTranslation(TranslationRequest request, Integer newEducationId) {
        this.translationRepository.save(TranslationEntity.builder()
                .entityId(newEducationId)
                .language(request.getLanguage())
                .field(request.getField())
                .value(request.getValue())
                .entityType(request.getEntityType())
                .build());
    }

}
