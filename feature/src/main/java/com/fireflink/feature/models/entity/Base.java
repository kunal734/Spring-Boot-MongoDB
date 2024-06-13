package com.fireflink.feature.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base {
    private String createdByName;
    private String createdByEmail;
    private LocalDate createdOn;
    private String modifiedByName;
    private String modifiedByEmail;
    private LocalDate modifiedOn;

    public void setCreatedEntity(String createdByName, String createdByEmail){
        this.createdByName=createdByName;
        this.createdByEmail=createdByEmail;
        this.createdOn= LocalDate.now();
    }

    public void setModifiedEntity(String modifiedByName, String modifiedByEmail){
        this.modifiedByName=modifiedByName;
        this.modifiedByEmail=modifiedByEmail;
        this.modifiedOn= LocalDate.now();
    }
}
