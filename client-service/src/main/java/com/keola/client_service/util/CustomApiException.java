package com.keola.client_service.util;

import com.keola.client_service.model.ErrorType;
import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    private final ErrorType errorType;

    public CustomApiException(ErrorType errorType) {
        super(errorType.getExceptionText());
        this.errorType = errorType;
    }
}