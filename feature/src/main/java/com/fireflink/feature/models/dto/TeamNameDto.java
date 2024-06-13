package com.fireflink.feature.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class TeamNameDto {
    @Indexed(unique = true)
    @NotNull(message = "Team name required")
    private String teamName;
}
