package com.tekpyramid.userdemo.controller;

import com.tekpyramid.userdemo.data.models.dto.ApiResponse;
import com.tekpyramid.userdemo.data.models.dto.UserDto;
import com.tekpyramid.userdemo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDto userDto,@PathVariable String id) {
        return userService.updateUser(userDto,id);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<ApiResponse> findByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<ApiResponse> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping("/verify-by-email")
    public ResponseEntity<ApiResponse> findByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return userService.findByEmailAndPassword(email,password);
    }

    @PostMapping("/verify-by-phone")
    public ResponseEntity<ApiResponse> findByPhoneAndPassword(@RequestParam long phone, @RequestParam String password) {
        return userService.findByPhoneAndPassword(phone, password);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll(@RequestParam(defaultValue = "0") int pageNo ,@RequestParam(defaultValue = "10") int size) {
        return userService.findAll(pageNo,size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable String id){
        return userService.deleteById(id);
    }
}
