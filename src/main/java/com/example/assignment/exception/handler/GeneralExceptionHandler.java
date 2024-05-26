package com.example.assignment.exception.handler;

import com.example.assignment.exception.BadRequestException;
import com.example.assignment.exception.GeneralException;
import com.example.assignment.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends RestErrorHandler{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException notFoundException){
        final ErrorResponse errorResponse = handleException(notFoundException);
        log.error("NotFoundException occurred {}", errorResponse, notFoundException);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(final BadRequestException badRequestException) {
        final ErrorResponse errorResponse = handleException(badRequestException);
        log.error("BadRequestException occurred {}", errorResponse, badRequestException);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse handleException(final GeneralException generalException) {
        return new ErrorResponse(generalException.getMessage(), generalException.getError().getCode(),
                LocalDateTime.now());
    }
}
