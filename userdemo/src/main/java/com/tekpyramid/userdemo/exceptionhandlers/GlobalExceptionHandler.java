package com.tekpyramid.userdemo.exceptionhandlers;

import com.tekpyramid.userdemo.data.models.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> notFound(UserNotFoundException exception) {
        ApiResponse structure = new ApiResponse();
        structure.setMessage(exception.getMessage());
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        structure.setBody("Invalid merchant Id");
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }
}