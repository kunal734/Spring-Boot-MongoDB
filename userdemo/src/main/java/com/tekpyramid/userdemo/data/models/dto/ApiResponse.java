package com.tekpyramid.userdemo.data.models.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private int statusCode;
    private String message;
    private Object body;
}
