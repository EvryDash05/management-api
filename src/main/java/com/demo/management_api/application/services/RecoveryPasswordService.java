package com.demo.management_api.application.services;

import com.demo.management_api.infraestructure.models.request.ResetPasswordRequest;
import com.demo.management_api.infraestructure.models.request.records.SendRequestPasswordRequest;
import com.demo.management_api.infraestructure.models.response.RecoveryPasswordResponse;

public interface RecoveryPasswordService {
    RecoveryPasswordResponse sendRequestRecoverPassword(SendRequestPasswordRequest request);
    String updatePassword(ResetPasswordRequest request, String token);
}
