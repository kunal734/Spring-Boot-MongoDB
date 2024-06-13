package com.tekpyramid.merchant.service.impl;

import com.tekpyramid.merchant.dao.MerchantDao;
import com.tekpyramid.merchant.data.models.dto.ApiResponse;
import com.tekpyramid.merchant.data.models.dto.MerchantDto;
import com.tekpyramid.merchant.data.models.entities.Merchant;
import com.tekpyramid.merchant.service.MerchantService;
import com.tekpyramid.merchant.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantDao merchantDao;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> saveMerchant(MerchantDto merchantDto) {
        Merchant merchant= modelMapper.map(merchantDto, Merchant.class);
        return ResponseUtil.createResponse(merchantDao.saveMerchant(merchant));
    }

    @Override
    public ResponseEntity<ApiResponse> updateMerchant(MerchantDto merchantDto, String id) {
        Merchant merchant=merchantDao.findById(id).orElseThrow(()-> new NoSuchElementException("Invalid Id"));
        modelMapper.map(merchantDto, merchant);
        return ResponseUtil.updateResponse(merchantDao.saveMerchant(merchant));
    }

    @Override
    public ResponseEntity<ApiResponse> findMerchantById(String id) {
        return merchantDao.findById(id).map(ResponseUtil::createOkResponse).orElseThrow(()-> new NoSuchElementException("Invalid Id"));
    }

    @Override
    public ResponseEntity<ApiResponse> findMerchantByName(String name) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> findMerchantByEmail(String email) {
        return merchantDao.findByEmail(email).map(ResponseUtil::createOkResponse).orElseThrow(()-> new NoSuchElementException("Invalid Email"));
    }

    @Override
    public ResponseEntity<ApiResponse> findMerchantByEmailAndPassword(String email, String password) {
        return merchantDao.findByEmailAndPassword(email, password).map(ResponseUtil::createOkResponse).orElseThrow(()-> new NoSuchElementException("Invalid Credentials"));
    }

    @Override
    public ResponseEntity<ApiResponse> findMerchantByPhoneAndPassword(long phone, String password) {
        return merchantDao.findByPhoneAndPassword(phone, password).map(ResponseUtil::createOkResponse).orElseThrow(()-> new NoSuchElementException("Invalid Credentials"));
    }


}
