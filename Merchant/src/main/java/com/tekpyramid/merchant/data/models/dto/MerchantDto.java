package com.tekpyramid.merchant.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {

    private String name;
    public String gender;
    private String email;
    private String password;
    private long phone;
}