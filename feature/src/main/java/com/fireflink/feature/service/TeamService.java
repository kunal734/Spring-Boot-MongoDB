package com.fireflink.feature.service;

import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.CommentDto;
import com.fireflink.feature.models.dto.TeamDto;
import org.springframework.http.ResponseEntity;

public interface TeamService {

    ResponseEntity<ApiResponse> saveTeam(TeamDto teamDto, String email);

    ResponseEntity<ApiResponse> updateTeamAccess(String email, String userEmail);

    ResponseEntity<ApiResponse> findAllTeams(int pageNo, int pageSize);

    ResponseEntity<ApiResponse> findAllTeamNames(int pageNo, int pageSize);

    ResponseEntity<ApiResponse> deleteTeam(String adminEmail, String id);

    ResponseEntity<ApiResponse> findByTeamName(String team);

    ResponseEntity<ApiResponse> findActiveUsersByTeam(String team);
}
