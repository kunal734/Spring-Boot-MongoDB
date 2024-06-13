package com.fireflink.feature.models.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserNameDto {

    private String name;
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$", message = "Invalid email format")
    private String email;

}
