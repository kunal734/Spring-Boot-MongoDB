package com.tekpyramid.userdemo.exceptionhandlers;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}

