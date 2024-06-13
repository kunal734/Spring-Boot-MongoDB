package com.tekpyramid.merchant.data.models.entities;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "merchants")
public class Merchant {

    @Id
    private String id;
    private String name;
    public String gender;
    private String email;
    private String password;
    private long phone;
    @Timestamp
    private LocalDate createdOn;

}
