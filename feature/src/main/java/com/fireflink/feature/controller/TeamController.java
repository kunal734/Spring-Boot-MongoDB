package com.fireflink.feature.controller;

import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.TeamDto;
import com.fireflink.feature.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveTeam(@RequestBody TeamDto teamDto,@RequestParam String adminEmail) {
        return teamService.saveTeam(teamDto,adminEmail);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTeamAccess(@RequestParam String userEmail,@RequestParam String adminEmail) {
        return teamService.updateTeamAccess(userEmail, adminEmail);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllTeams(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize) {
        return teamService.findAllTeams(pageNo, pageSize);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteTeam(@RequestParam String adminEmail,@RequestParam String id){
        return teamService.deleteTeam(adminEmail,id);
    }

    @GetMapping("/findAllTeamNames")
    public ResponseEntity<ApiResponse> findAllTeamNames(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize){
        return teamService.findAllTeamNames(pageNo, pageSize);
    }

    @GetMapping("/activeUsers")
    public ResponseEntity<ApiResponse> findActiveUsersByTeam(@RequestParam String team){
        return teamService.findActiveUsersByTeam(team);
    }

    @GetMapping("/teamName")
    public ResponseEntity<ApiResponse> findByTeamName(@RequestParam String team){
        return teamService.findByTeamName(team);
    }
}
