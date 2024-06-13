package com.tekpyramid.merchant.controller;

import com.tekpyramid.merchant.data.models.dto.ApiResponse;
import com.tekpyramid.merchant.data.models.dto.MerchantDto;
import com.tekpyramid.merchant.service.MerchantService;
import com.tekpyramid.merchant.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveMerchant(@RequestBody MerchantDto merchantDto){
        return merchantService.saveMerchant(merchantDto);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateMerchant(@RequestBody MerchantDto merchantDto, @PathVariable String id){
        return merchantService.updateMerchant(merchantDto,id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findMerchantById(@PathVariable String id){
        return merchantService.findMerchantById(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findMerchantByEmail(@PathVariable String email){
        return merchantService.findMerchantByEmail(email);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> findMerchantByEmailAndPassword(@RequestParam String email, @RequestParam String password){
        return merchantService.findMerchantByEmailAndPassword(email, password);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> findMerchantByPhoneAndPassword(@RequestParam long phone, @RequestParam String password){
        return merchantService.findMerchantByPhoneAndPassword(phone, password);
    }

//    public ResponseEntity<ApiResponse> deleteMerchantById(@RequestParam String id){
//        return null;
//    }
}
