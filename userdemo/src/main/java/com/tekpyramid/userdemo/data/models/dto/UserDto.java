package com.tekpyramid.userdemo.data.models.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    @NotBlank(message = "Please Enter Name to proceed")
    private String name;
//    @Email(message = "Please Enter Email to proceed")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9+_.-]+[.]+[a-zA-Z0-9.-]{2,4}$",message = "Please provide a Valid Email")
    private String email;
    private String password;
    private long mobileNumber;
    private List<String> skills;
}
