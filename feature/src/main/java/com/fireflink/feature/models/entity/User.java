package com.fireflink.feature.models.entity;

import com.fireflink.feature.util.Access;
import com.fireflink.feature.util.AccountStatus;
import com.fireflink.feature.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User extends Base{

    @Id
    private String id;
    private String name;
    @Transient
    private long noOfTickets;
    private Role role;
    @Indexed(unique = true)
    private String email;
    private String password;
    @Indexed(unique = true)
    private long phone;
    private Access access;
    private AccountStatus status;
    private String team;
}
