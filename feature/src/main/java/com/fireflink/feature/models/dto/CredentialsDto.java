package com.fireflink.feature.models.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CredentialsDto {
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$", message = "Invalid email format")
    private String email;
}
