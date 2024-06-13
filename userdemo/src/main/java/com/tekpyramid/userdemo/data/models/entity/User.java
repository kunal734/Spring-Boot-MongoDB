package com.tekpyramid.userdemo.data.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userdb")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private long mobileNumber;
    private List<String> skills;
    private String createdOn;

}
