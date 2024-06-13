package com.tekpyramid.reservation.data.models.dto;

import lombok.Data;

@Data
public class AdminDto {

    private String name;
    private long phone;
    private String gst_number;
    private String travels_name;
    private String email;
    private String password;
}
