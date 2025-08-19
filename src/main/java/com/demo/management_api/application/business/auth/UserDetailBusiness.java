package com.demo.management_api.application.business.auth;

import com.demo.management_api.application.services.DistrictRepository;
import com.demo.management_api.application.services.UserDetailService;
import com.demo.management_api.commons.utils.Utils;
import com.demo.management_api.domain.entity.DistrictEntity;
import com.demo.management_api.domain.entity.UserDetailEntity;
import com.demo.management_api.domain.entity.UserEntity;
import com.demo.management_api.domain.repository.auth.UserDetailRepository;
import com.demo.management_api.infraestructure.models.request.AuthUserRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailBusiness implements UserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final DistrictRepository districtRepository;

    @Override
    public void createUserDetail(AuthUserRegisterRequest request, UserEntity user) {
        DistrictEntity findDistrict = this.districtRepository.findById(request.getDistrictId())
                .orElseThrow(() -> new IllegalArgumentException("District not found"));
        this.userDetailRepository.save(UserDetailEntity.builder()
                .userDetailId(Utils.generateCode("STD"))
                .user(user)
                .name(request.getName())
                .lastName(request.getLastName())
                .district(findDistrict)
                .build());
    }


}
