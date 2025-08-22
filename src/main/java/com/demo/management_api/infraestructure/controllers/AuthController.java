package com.demo.management_api.infraestructure.controllers;

import com.demo.management_api.application.business.auth.AuthBusiness;
import com.demo.management_api.application.business.auth.RefreshTokenBusiness;
import com.demo.management_api.application.business.auth.UserBusiness;
import com.demo.management_api.application.services.RecoveryPasswordService;
import com.demo.management_api.commons.constants.ApiConstants;
import com.demo.management_api.infraestructure.models.CustomUserDetails;
import com.demo.management_api.infraestructure.models.request.AuthUserRegisterRequest;
import com.demo.management_api.infraestructure.models.request.ResetPasswordRequest;
import com.demo.management_api.infraestructure.models.request.records.AuthLoginRequest;
import com.demo.management_api.infraestructure.models.request.records.SendRequestPasswordRequest;
import com.demo.management_api.infraestructure.models.response.AuthResponse;
import com.demo.management_api.infraestructure.models.response.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiConstants.AUTH_PREFIX_ENDPOINT)
public class AuthController {

    private final RefreshTokenBusiness refreshTokenBusiness;
    private final AuthBusiness authBusiness;
    private final UserBusiness userBusiness;
    private final RecoveryPasswordService recoveryPasswordService;

    @PreAuthorize(ApiConstants.HAS_ROLE_ADMIN)
    @PostMapping(ApiConstants.REGISTER_ENDPOINT)
    public ResponseEntity<String> register(@RequestBody AuthUserRegisterRequest request){
        return ResponseEntity.ok(this.authBusiness.createUser(request));
    }

    @PostMapping(ApiConstants.LOGIN_ENDPOINT)
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest request){
        return ResponseEntity.ok(this.authBusiness.login(request));
    }

    @PostMapping(ApiConstants.REFRESH_TOKEN_ENDPOINT)
    public ResponseEntity<AuthResponse> refreshToken(){
        return ResponseEntity.ok(this.refreshTokenBusiness.createNewAccessToken());
    }

    @GetMapping("/find-profile")
    public ResponseEntity<UserDetailsResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(this.userBusiness.findUserData(userDetails.getUserId()));
    }

    @PostMapping(ApiConstants.SEND_EMAIL_PASSWORD_RECOVERY_ENDPOINT)
    public ResponseEntity<String> recoveryPasswordRequest(@RequestBody SendRequestPasswordRequest request){
        return ResponseEntity.ok(this.recoveryPasswordService.sendRequestRecoverPassword(request).token);
    }

    @PatchMapping(ApiConstants.RESET_PASSWORD_ENDPOINT)
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request){
        Map<String, String> response = new HashMap<>();
        this.recoveryPasswordService.updatePassword(request, token);
        return ResponseEntity.ok(response.put("message", "Password updated successfully"));
    }

    @PostMapping(ApiConstants.TEST_ENDPOINT)
    public ResponseEntity<String> testing(){
        return ResponseEntity.ok("Status, OK!");
    }

}
