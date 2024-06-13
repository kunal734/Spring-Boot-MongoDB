package com.fireflink.feature.models.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketDto {
    private String title;
    @Size(min = 2, max = 150, message = "summary must be between 2 and 150 characters")
    private String summary;
    private String assigneeName;
    private String assigneeEmail;
    private String assigneeTeam;
    private String supportMember;
    private String issueRelated;
}
