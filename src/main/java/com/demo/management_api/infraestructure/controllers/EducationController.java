package com.demo.management_api.infraestructure.controllers;

import com.demo.management_api.application.services.EducationService;
import com.demo.management_api.commons.constants.ApiConstants;
import com.demo.management_api.infraestructure.models.request.EducationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class EducationController {

    public final EducationService educationService;

    @PreAuthorize(ApiConstants.HAS_ROLE_ADMIN)
    @PostMapping(ApiConstants.REGISTER_EDUCATION)
    public ResponseEntity<String> registerEducation(EducationRequest request) {
        return ResponseEntity.ok(this.educationService.registerEducation(request));
    }

}
