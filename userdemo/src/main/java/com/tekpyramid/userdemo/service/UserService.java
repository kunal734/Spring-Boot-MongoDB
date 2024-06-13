package com.tekpyramid.userdemo.service;

import com.tekpyramid.userdemo.data.models.dto.ApiResponse;
import com.tekpyramid.userdemo.data.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<ApiResponse> saveUser(UserDto user);

    public ResponseEntity<ApiResponse> updateUser(UserDto user,String id);

    public ResponseEntity<ApiResponse> findById(String id);

    public ResponseEntity<ApiResponse> findByName(String name);

    public ResponseEntity<ApiResponse> findByEmail(String email);

    public ResponseEntity<ApiResponse> findByEmailAndPassword(String email, String password);

    public ResponseEntity<ApiResponse> findByPhoneAndPassword(long phone, String password);

    public ResponseEntity<ApiResponse> findAll(int pageNo,int size );

    public ResponseEntity<ApiResponse> deleteById(String id);
}