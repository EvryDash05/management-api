package com.demo.management_api.application.services;

import com.demo.management_api.infraestructure.models.request.EducationRequest;
import com.demo.management_api.infraestructure.models.response.EducationDetailResponse;

import java.util.List;

public interface EducationService {
    String registerEducation(EducationRequest request);
    EducationDetailResponse getEducationWithDetailsByLanguage(Integer id, String languageCode);
    List<EducationDetailResponse> getAllEducations();
}
