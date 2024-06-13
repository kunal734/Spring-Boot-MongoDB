package com.tekpyramid.merchant.service;

import com.tekpyramid.merchant.data.models.dto.ApiResponse;
import com.tekpyramid.merchant.data.models.dto.MerchantDto;
import org.springframework.http.ResponseEntity;

public interface MerchantService {

    public ResponseEntity<ApiResponse> saveMerchant(MerchantDto merchantDto);

    public ResponseEntity<ApiResponse> updateMerchant(MerchantDto merchantDto,String id);

    public ResponseEntity<ApiResponse> findMerchantById(String id);

    public ResponseEntity<ApiResponse> findMerchantByName(String name);

    public ResponseEntity<ApiResponse> findMerchantByEmail(String email);

    public ResponseEntity<ApiResponse> findMerchantByEmailAndPassword(String email, String password);

    public ResponseEntity<ApiResponse> findMerchantByPhoneAndPassword(long phone, String password);
}
