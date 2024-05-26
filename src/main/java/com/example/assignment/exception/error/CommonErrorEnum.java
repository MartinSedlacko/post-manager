package com.example.assignment.exception.error;

import java.util.Locale;

public enum CommonErrorEnum implements ErrorCode{

    NOT_FOUND("Data not found"),
    BAD_REQUEST("Bad request");

    public static final String COMMON_ERROR_CODE_PREFIX = "error.common.";

    private final String code;
    private final String description;

    CommonErrorEnum(final String description) {
        this.code = COMMON_ERROR_CODE_PREFIX + name().toLowerCase(Locale.ENGLISH).replace('_', '.');
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
