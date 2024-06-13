package com.fireflink.feature.service;

import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ApiResponse> saveUser(UserDto user, String email);

    ResponseEntity<ApiResponse> updateTeamName(String adminEmail, String userEmail, String teamName);

    ResponseEntity<ApiResponse> findById(String id);

    ResponseEntity<ApiResponse> findByName(String name);

    ResponseEntity<ApiResponse> findByEmail(String email);

    ResponseEntity<ApiResponse> findByTeamName(String teamName);

    ResponseEntity<ApiResponse> findAllActiveUsers(int pageNo, int pageSize);

    ResponseEntity<ApiResponse> findAllUsers(int pageNo, int pageSize);

    ResponseEntity<ApiResponse> deleteUserByEmail(String email);

    ResponseEntity<ApiResponse> setPassword(String email, String password);

    ResponseEntity<ApiResponse> getUsersAssignedTo();

    ResponseEntity<ApiResponse> getUsersCreatedBy();

    ResponseEntity<ApiResponse> getSupportMembers();

    ResponseEntity<ApiResponse> findByEmailAndPassword(String email, String password);

    ResponseEntity<ApiResponse> searchUsersToAssign(String name, int pageNo, int pageSize);
}