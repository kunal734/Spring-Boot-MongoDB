package com.fireflink.feature.models.dto;

import com.fireflink.feature.models.entity.Base;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class TeamDto {
    @Indexed(unique = true)
    @NotNull(message = "Team name required")
    private String teamName;
    @NotNull(message = "Description required")
    private String description;
    @Transient
    private int noOfUsers;
}
