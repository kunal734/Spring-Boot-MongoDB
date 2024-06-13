package com.fireflink.feature.models.entity;

import com.fireflink.feature.util.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tickets")
public class Ticket extends Base{

    @Id
    private String ticketId;
    private String title;
    private String summary;
    private String severity;
    private String priority;
    private String description;
    private String license;
    private String assigneeName;
    private String assigneeEmail;
    private String assigneeTeam;
    private String supportMember;
    private String issueRelated;
    private AccountStatus status;
    private List<Comment> comments;

    public void setAssigneeDetails (String assigneeName, String assigneeEmail, String assigneeTeam){
        this.assigneeName=assigneeName;
        this.assigneeEmail=assigneeEmail;
        this.assigneeTeam= assigneeTeam;
    }
}