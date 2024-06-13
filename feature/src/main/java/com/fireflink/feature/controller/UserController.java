package com.fireflink.feature.controller;

import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.models.dto.UserDto;
import com.fireflink.feature.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("saveUser")
    public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody UserDto userDto, @RequestHeader String email) {
        return userService.saveUser(userDto, email);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllUsers(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize) {
        return userService.findAllUsers(pageNo, pageSize);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponse> setPassword(@RequestHeader String email, @RequestHeader String password) {
        return userService.setPassword(email, password);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateTeamName(@RequestParam String adminEmail, @RequestParam String userEmail, @RequestParam String teamName) {
        return userService.updateTeamName(adminEmail, userEmail, teamName);
    }

    @GetMapping("/activeUsers")
    public ResponseEntity<ApiResponse> findAllActiveUsers(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize) {
        return userService.findAllActiveUsers(pageNo, pageSize);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchUsersToAssign(@RequestParam String name, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        return userService.searchUsersToAssign(name, pageNo, pageSize);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> findByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteUserByEmail(@RequestParam String email){
        return userService.deleteUserByEmail(email);
    }

    @GetMapping("/getUsersAssignedTo")
    public ResponseEntity<ApiResponse> getUsersAssignedTo(){
        return  userService.getUsersAssignedTo();
    }

    @GetMapping("/getUsersCreatedBy")
    public ResponseEntity<ApiResponse> getUsersCreatedBy(){
        return userService.getUsersCreatedBy();
    }

    @GetMapping("/getSupportMembers")
    public ResponseEntity<ApiResponse> getSupportMembers(){
        return userService.getSupportMembers();
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity<ApiResponse> findByEmailAndPassword(@RequestParam String email, @RequestParam String password){
        return userService.findByEmailAndPassword(email, password);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse> findById(@PathVariable String id) {
//        return userService.findById(id);
//    }

//    @GetMapping("/Phone/{phone}")
//    public ResponseEntity<ApiResponse> findByPhone(@PathVariable long phone) {
//        return userService.findByPhone(phone);
//    }

}
