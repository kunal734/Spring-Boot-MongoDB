package com.tekpyramid.merchant.data.models.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private String message;
    private Object body;
    private int statusCode;
}
