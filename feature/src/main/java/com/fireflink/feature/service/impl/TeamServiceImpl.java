package com.fireflink.feature.service.impl;

import com.fireflink.feature.dao.TeamDao;
import com.fireflink.feature.dao.UserDao;
import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.TeamDto;
import com.fireflink.feature.models.dto.TeamNameDto;
import com.fireflink.feature.models.entity.Team;
import com.fireflink.feature.models.entity.User;
import com.fireflink.feature.service.TeamService;
import com.fireflink.feature.util.Access;
import com.fireflink.feature.util.AccountStatus;
import com.fireflink.feature.util.ResponseUtil;
import com.fireflink.feature.util.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<ApiResponse> saveTeam(TeamDto teamDto, String adminEmail) {
        Optional<User> admin=userDao.findByEmail(adminEmail);
        if(admin.isPresent() && admin.get().getRole().equals(Role.Admin)){
            Team team=modelMapper.map(teamDto,Team.class);
            team.setCreatedEntity(admin.get().getName(), admin.get().getEmail());
            return ResponseUtil.getCreatedResponse(teamDao.saveTeam(team));
        }
        return ResponseUtil.getBadRequestResponse("Only admin can create teams");
    }

    @Override
    public ResponseEntity<ApiResponse> updateTeamAccess(String adminEmail, String userEmail) {
        Optional<User> admin=userDao.findByEmail(adminEmail);
        if(admin.isPresent() && admin.get().getAccess().equals(Access.EditAccess)){
            Optional<User> user=userDao.findByEmail(userEmail);
            if(user.isPresent()){
                user.get().setAccess(user.get().getAccess() == Access.EditAccess ? Access.ViewAccess:Access.EditAccess);
                user.get().setModifiedEntity(admin.get().getName(), admin.get().getEmail());
                userDao.saveUser(user.get());
                return ResponseUtil.getOkResponse(user.get());
            }
            return ResponseUtil.getBadRequestResponse("User not found");
        }
        return ResponseUtil.getBadRequestResponse("Only admin can update team access");
    }

    @Override
    public ResponseEntity<ApiResponse> findAllTeams(int pageNo, int pageSize) {
        Page<Team> receivedTeams = teamDao.findAllTeamNames(pageNo, pageSize);
        if(receivedTeams.isEmpty()){
            return ResponseUtil.getNoContentResponse("No teams found");
        }
        else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Query query = new Query().with(pageable);
            List<Team> teams = mongoTemplate.find(query, Team.class);
            long count = mongoTemplate.count(Query.query(new Criteria()), Team.class);
            return ResponseUtil.getOkResponse(new PageImpl<>(teams, pageable, count));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> findAllTeamNames(int pageNo, int pageSize) {
        Page<Team> receivedTeams = teamDao.findAllTeamNames(pageNo, pageSize);
        if(receivedTeams.isEmpty()){
            return ResponseUtil.getNoContentResponse("No teams found");
        }
        else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Query query = new Query().with(pageable);
            List<Team> teams = mongoTemplate.find(query, Team.class);
            List<TeamNameDto> teamNames = teams.stream().map(team -> new TeamNameDto(team.getTeamName())).collect(Collectors.toList());
            long count = mongoTemplate.count(Query.query(new Criteria()), Team.class);
            return ResponseUtil.getOkResponse(new PageImpl<>(teamNames, pageable, count));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteTeam(String adminEmail, String id) {
        Optional<User> admin = userDao.findByEmail(adminEmail);
        if(admin.isPresent() && admin.get().getRole()==Role.Admin){
            return ResponseUtil.getOkResponse(teamDao.deleteTeam(id));
        }
        return ResponseUtil.getBadRequestResponse("Admin can only delete team");
    }

    @Override
    public ResponseEntity<ApiResponse> findByTeamName(String team) {
        return ResponseUtil.getOkResponse(teamDao.findByTeamName(team));
    }

    @Override
    public ResponseEntity<ApiResponse> findActiveUsersByTeam(String team) {
        Optional<Team> optionalTeam=teamDao.findByTeamName(team);
        if(optionalTeam.isPresent()) {
            Query query = new Query();
            query.addCriteria(new Criteria().andOperator(Criteria.where("team").is(team), Criteria.where("status").is(AccountStatus.ACTIVE)));
            return ResponseUtil.getOkResponse(mongoTemplate.find(query, User.class));
        }
        return ResponseUtil.getBadRequestResponse("Team not found");
    }
}
