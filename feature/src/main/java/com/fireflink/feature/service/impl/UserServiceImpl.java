package com.fireflink.feature.service.impl;

import com.fireflink.feature.dao.TeamDao;
import com.fireflink.feature.dao.UserDao;
import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.UserDto;
import com.fireflink.feature.models.dto.UserNameDto;
import com.fireflink.feature.models.entity.Team;
import com.fireflink.feature.models.entity.Ticket;
import com.fireflink.feature.models.entity.User;
import com.fireflink.feature.service.UserService;
import com.fireflink.feature.util.Access;
import com.fireflink.feature.util.AccountStatus;
import com.fireflink.feature.util.ResponseUtil;
import com.fireflink.feature.util.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final TeamDao teamDao;
    private final ModelMapper modelMapper;
    private final EmailServiceImpl emailServiceImpl;
    private String format = "Hi %s";
    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<ApiResponse> saveUser(UserDto userDto, String email) {
        Optional<User> admin=userDao.findByEmail(email);
        if(admin.isPresent() && admin.get().getRole().equals(Role.Admin)){
            Optional<Team> team=teamDao.findByTeamName(userDto.getTeam());
            if(team.isPresent()){
                Optional<User> receivedUser=userDao.findByEmail(userDto.getEmail());
                if(receivedUser.isEmpty()) {
                    User user = modelMapper.map(userDto, User.class);
                    user.setId(UUID.randomUUID().toString());
                    user.setStatus(AccountStatus.ACTIVATION_PENDING);
                    user.setAccess(Access.ViewAccess);
                    user.setRole(Role.Internal_Team);
                    user.setCreatedEntity(admin.get().getName(), admin.get().getEmail());
                    String subject = String.format(format, user.getName());
                    emailServiceImpl.sendEmail(userDto.getEmail(),subject,"Your Account has been created...");
                    return ResponseUtil.getCreatedResponse(userDao.saveUser(user));
                }
                return ResponseUtil.getBadRequestResponse("User with this email already exists");
            }
            return ResponseUtil.getBadRequestResponse("Team not found");
        }
        return ResponseUtil.getBadRequestResponse("Sorry, you cannot add a user...Please provide valid email");
    }

    @Override
    public ResponseEntity<ApiResponse> updateTeamName(String adminEmail, String userEmail, String teamName) {
        Optional<User> admin = userDao.findByEmail(adminEmail);
        if(admin.isPresent() && admin.get().getRole().equals(Role.Admin)){
            Optional<Team> team=teamDao.findByTeamName(admin.get().getTeam());
            if(team.isPresent()){
                User user= userDao.findByEmail(userEmail).get();
                user.setTeam(teamName);
                return ResponseUtil.getAcceptedResponse(user);
            }
            return ResponseUtil.getBadRequestResponse("Team not found");
        }
        return ResponseUtil.getBadRequestResponse("Sorry, you cannot update a team...Please provide valid email");
    }

    @Override
    public ResponseEntity<ApiResponse> findById(String id) {
        return ResponseUtil.getOkResponse(userDao.findById(id));
    }

    @Override
    public ResponseEntity<ApiResponse> findByName(String name){
        return ResponseUtil.getOkResponse(userDao.findByName(name));
    }

    @Override
    public ResponseEntity<ApiResponse> findByEmail(String email) {
        return ResponseUtil.getOkResponse(userDao.findByEmail(email));
    }

    @Override
    public ResponseEntity<ApiResponse> findByTeamName(String teamName) {
        return ResponseUtil.getOkResponse(userDao.findByTeam(teamName));
    }

    @Override
    public ResponseEntity<ApiResponse> findAllActiveUsers(int pageNo, int pageSize) {
        Page<User> receivedUsers = userDao.findAllActiveUsers(pageNo, pageSize);
        if(receivedUsers.isEmpty()){
            return ResponseUtil.getNoContentResponse("No users found");
        }
        return ResponseUtil.getOkResponse(receivedUsers);
    }

    @Override
    public ResponseEntity<ApiResponse> findAllUsers(int pageNo, int pageSize) {
        Page<User> receivedUsers = userDao.findAllUsers(pageNo, pageSize);
        if(receivedUsers.isEmpty()){
            return ResponseUtil.getNoContentResponse("No users found");
        }
        return ResponseUtil.getOkResponse(receivedUsers);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUserByEmail(String email) {
        Optional<User> receivedUser = userDao.findByEmail(email);
        if(receivedUser.isPresent()){
            return ResponseUtil.getOkResponse(userDao.deleteUserByEmail(email));
        }
        return ResponseUtil.getBadRequestResponse("User not found");
    }

    @Override
    public ResponseEntity<ApiResponse> setPassword(String email, String password) {
        return ResponseUtil.getOkResponse(userDao.setPassword(email,password));
    }

    @Override
    public ResponseEntity<ApiResponse> getUsersAssignedTo() {
        List<Ticket> tickets = mongoTemplate.findAll(Ticket.class);
        Set<UserNameDto> userNameDto = tickets.stream().map(this::mapNameEmailAssignedTo).collect(Collectors.toSet());
        if(userNameDto.isEmpty()){
            return ResponseUtil.getNoContentResponse("No users found");
        }
        return ResponseUtil.getOkResponse(userNameDto);
    }

    private UserNameDto mapNameEmailAssignedTo(Ticket ticket) {
        UserNameDto userNameDto = new UserNameDto();
        userNameDto.setEmail(ticket.getAssigneeEmail());
        userNameDto.setName(ticket.getAssigneeName());
        return userNameDto;
    }

    @Override
    public ResponseEntity<ApiResponse> getUsersCreatedBy() {
        List<Ticket> tickets = mongoTemplate.findAll(Ticket.class);
        Set<UserNameDto> userNameDto = tickets.stream().map(this::mapNameEmail).collect(Collectors.toSet());
        if(userNameDto.isEmpty()){
            return ResponseUtil.getNoContentResponse("No users found");
        }
        return ResponseUtil.getOkResponse(userNameDto);
    }

    @Override
    public ResponseEntity<ApiResponse> getSupportMembers() {
        List<Ticket> tickets = mongoTemplate.findAll(Ticket.class);
        Set<String> userNameDto=tickets.stream().map(Ticket::getSupportMember).collect(Collectors.toSet());
        return ResponseUtil.getOkResponse(userNameDto);
    }

    @Override
    public ResponseEntity<ApiResponse> findByEmailAndPassword(String email, String password) {
        return ResponseUtil.getOkResponse(userDao.findByEmailAndPassword(email, password));
    }

    public UserNameDto mapNameEmail(Ticket ticket) {
        UserNameDto userNameDto = new UserNameDto();
        userNameDto.setEmail(ticket.getCreatedByEmail());
        userNameDto.setName(ticket.getCreatedByName());
        return userNameDto;
    }

    public ResponseEntity<ApiResponse> searchUsersToAssign(String name, int pageNo, int pageSize) {
        List<User> userList = userDao.searchUsersToAssign(name,pageNo, pageSize);
        return ResponseUtil.getOkResponse(new PageImpl<>(userList, PageRequest.of(pageNo, pageSize), userList.size()));
    }
}
