package com.example.assignment.exception;

import com.example.assignment.exception.error.ErrorCode;

public class BadRequestException extends GeneralException{
    public BadRequestException(ErrorCode error, String message) {
        super(error, message);
    }
}
