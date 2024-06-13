package com.fireflink.feature.exception;


import com.fireflink.feature.models.dto.ApiResponse;
import com.fireflink.feature.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.NoSuchElementException;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> userNotFound(NoSuchElementException exception){
        return ResponseUtil.getNoContentResponse(exception.getMessage());
    }
}

