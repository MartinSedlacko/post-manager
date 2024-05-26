package com.example.assignment.exception;

import com.example.assignment.exception.error.ErrorCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static java.util.Objects.requireNonNull;

@Getter
public class GeneralException extends RuntimeException {

    private final ErrorCode error;

    public GeneralException(final ErrorCode error, final String message) {
        this(error, message, null);
    }

    public GeneralException(final ErrorCode error, final String message, final Throwable throwable) {
        super(message, throwable);
        this.error = requireNonNull(error);
    }

    @Override
    public String getMessage() {
        final String superMsg = super.getMessage();
        if (StringUtils.isNoneEmpty(superMsg)) {
            return superMsg;
        }
        return "[" + getError().getCode() + "] " + getError().getDescription();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("code", error.getCode())
                .append("description", error.getDescription())
                .toString();
    }
}
