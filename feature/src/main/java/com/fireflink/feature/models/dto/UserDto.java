package com.fireflink.feature.models.dto;

import com.fireflink.feature.models.entity.Base;
import com.fireflink.feature.util.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class UserDto {
    @NotNull(message = "Name should not be null or Empty")
    private String name;
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$",
            message = "Invalid email format")
    private String email;
    @Indexed(unique = true)
    @Min(value = 6000000000L,message = "Phone number should be range from 6000000000 to 9999999999")
    @Max(value = 9999999999L,message = "Phone number should be range from 6000000000 to 9999999999")
    private long phone;
    private Role role;
    private String team;
}
