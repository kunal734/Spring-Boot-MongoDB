package com.fireflink.feature.models.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserUpdateDto {
    @NotNull(message = "Name should not be null or Empty")
    @Pattern( regexp = "^[a-zA-Z0-9_]{3,30}$",
            message = "Username must contain only letters, numbers, or underscores")
    private String name;
    @Indexed(unique = true)
    @Min(value = 6000000000L,message = "phone number should be range from 6000000000 to 9999999999")
    @Max(value = 9999999999L,message = "phone number should be range from 6000000000 to 9999999999")
    private long phone;
}
