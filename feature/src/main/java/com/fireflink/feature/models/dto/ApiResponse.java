package com.fireflink.feature.models.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private int statusCode;
    private String message;
    private Object body;
}
