package com.keola.product_service.config;

import com.keola.product_service.model.ErrorType;
import com.keola.product_service.util.CustomApiException;
import com.keola.product_service.util.enums.ErrorCategoryEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    @DisplayName("Return a response entity with the error type when handle custom api exception")
    void returnAResponseEntityWithTheErrorTypeWhenHandleCustomApiException() {
        ErrorType errorType = ErrorType.builder()
                .exceptionId(ErrorCategoryEnum.EXTERNAL_ERROR.getExceptionId())
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        CustomApiException customApiException = new CustomApiException(errorType);

        ResponseEntity<ErrorType> response = globalExceptionHandler.handleCustomApiException(customApiException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorType, response.getBody());
    }

    @Test
    @DisplayName("Return a response entity with the error type when handle generic exception")
    void returnAResponseEntityWithTheErrorTypeWhenHandleGenericException() {
        Exception exception = new Exception("Generic error");

        ResponseEntity<ErrorType> response = globalExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Generic error", response.getBody().getExceptionDetails().get(0).getDescription());
    }

}