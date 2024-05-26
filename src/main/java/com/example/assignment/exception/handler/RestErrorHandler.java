package com.example.assignment.exception.handler;

import io.micrometer.common.lang.Nullable;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

import static com.example.assignment.exception.error.CommonErrorEnum.COMMON_ERROR_CODE_PREFIX;


@ControllerAdvice
@Slf4j
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex, @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request) {
        HttpStatus status = HttpStatus.resolve(statusCode.value());
        String statusName;

        if (status != null) {
            statusName = status.name();
        } else {
            statusName = String.valueOf(statusCode.value());
        }

        final String errorCode = COMMON_ERROR_CODE_PREFIX + statusName.toLowerCase(Locale.ENGLISH)
                .replace('_', '.');
        final ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorCode, LocalDateTime.now());
        log.error("Exception occurred {}", errorResponse, ex);
        return new ResponseEntity<>(errorResponse, statusCode);
    }
}
