package com.fireflink.feature.util;

import com.fireflink.feature.models.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ApiResponse initResponseStructure(){
        return  new ApiResponse();
    }

    public static ResponseEntity<ApiResponse> getCreatedResponse(Object response){
        ApiResponse apiResponse = initResponseStructure();
        apiResponse.setMessage("Created");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse> getAcceptedResponse(Object response){
        ApiResponse apiResponse = initResponseStructure();
        apiResponse.setMessage("Updated");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    public static ResponseEntity<ApiResponse> getOkResponse(Object response){
        ApiResponse apiResponse = initResponseStructure();
        apiResponse.setMessage("Found");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> getBadRequestResponse(Object response){
        ApiResponse apiResponse=initResponseStructure();
        apiResponse.setMessage("Bad Request");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ApiResponse> getNoContentResponse(Object response){
        ApiResponse apiResponse=initResponseStructure();
        apiResponse.setMessage("Deleted");
        apiResponse.setBody(response);
        apiResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }
}
