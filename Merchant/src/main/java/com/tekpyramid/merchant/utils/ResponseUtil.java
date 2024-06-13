package com.tekpyramid.merchant.utils;

import com.tekpyramid.merchant.data.models.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ApiResponse initResponseStructure(){
        return  new ApiResponse();
    }

    public static ResponseEntity<ApiResponse> createResponse(Object response){
        ApiResponse apiResponse = initResponseStructure();
        apiResponse.setMessage("Merchant Created");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse> updateResponse(Object response){
        ApiResponse apiResponse = initResponseStructure();
        apiResponse.setMessage("Merchant Updated");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    public static ResponseEntity<ApiResponse> createOkResponse(Object response){
        ApiResponse apiResponse = initResponseStructure();
        apiResponse.setMessage("Merchant Found");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> createNotFoundException(Object response){
        ApiResponse apiResponse=initResponseStructure();
        apiResponse.setMessage("Merchant Not Found");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
