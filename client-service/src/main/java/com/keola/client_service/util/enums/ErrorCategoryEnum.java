package com.keola.client_service.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCategoryEnum {

    INVALID_REQUEST("invalid-request", HttpStatus.BAD_REQUEST.value(), "SVC0001"),
    ARGUMENT_MISMATCH("argument-mismatch", HttpStatus.BAD_REQUEST.value(), "SVC0001"),
    INVALID_HEADER("invalid-header", HttpStatus.BAD_REQUEST.value(), "SVC0003"),
    UNAUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED.value(), "SVC0004"),
    FORBIDDEN("forbidden", HttpStatus.FORBIDDEN.value(), "SVC0005"),
    RESOURCE_NOT_FOUND("resource-not-found", HttpStatus.NOT_FOUND.value(), "SVC0006"),
    CONFLICT("conflict", HttpStatus.CONFLICT.value(), "SVC0007"),
    PRECONDITION_FAILED("precondition-failed", HttpStatus.PRECONDITION_FAILED.value(), "SVC0008"),
    EXTERNAL_ERROR("external-error", HttpStatus.INTERNAL_SERVER_ERROR.value(), "SVR1000"),
    HOST_NOT_FOUND("host-not-found", HttpStatus.INTERNAL_SERVER_ERROR.value(), "SVR1001"),
    UNEXPECTED("unexpected", HttpStatus.INTERNAL_SERVER_ERROR.value(), "SVR1002"),
    NOT_IMPLEMENTED("not-implemented", HttpStatus.NOT_IMPLEMENTED.value(), "SVR1003"),
    SERVICE_UNAVAILABLE("service-unavailable", HttpStatus.SERVICE_UNAVAILABLE.value(), "SVR1004"),
    EXTERNAL_TIMEOUT("external-timeout", HttpStatus.SERVICE_UNAVAILABLE.value(), "SVR1005");

    private final String property;
    private final int httpStatus;
    private final String exceptionId;
}