package com.example.assignment.exception;


import com.example.assignment.exception.error.ErrorCode;

public class NotFoundException extends GeneralException {

    public NotFoundException(ErrorCode error, String message) {
        super(error, message);
    }
}
