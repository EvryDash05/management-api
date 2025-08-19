package com.demo.management_api.application.business;

import com.demo.management_api.application.services.EducationService;
import com.demo.management_api.application.services.TranslationService;
import com.demo.management_api.domain.entity.EducationEntity;
import com.demo.management_api.domain.repository.EducationRepository;
import com.demo.management_api.infraestructure.models.request.EducationRequest;
import com.demo.management_api.infraestructure.models.response.EducationDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationBusiness implements EducationService {

    private final EducationRepository educationRepository;
    private final TranslationService translationService;

    @Override
    @Transactional
    public String registerEducation(EducationRequest request) {
        EducationEntity newEducation = mapToTranslationEntity(request.getOrderNumber());
        this.translationService.registerTranslation(request.getTranslation(), newEducation.getId());
        log.info("New education created");
        return "New education created";
    }

    @Override
    public EducationDetailResponse getEducationWithDetailsByLanguage(Integer id, String languageCode) {
        return null;
    }

    @Override
    public List<EducationDetailResponse> getAllEducations() {
        return List.of();
    }

    private EducationEntity mapToTranslationEntity(int orderNumber) {
        return this.educationRepository.save(EducationEntity.builder()
                .orderNum(orderNumber)
                .build());
    }

}
