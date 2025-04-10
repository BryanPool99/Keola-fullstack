package com.keola.product_service.util;

import com.keola.product_service.model.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class CustomApiExceptionTest {

    @Test
    @DisplayName("Return CustomApiException when create a new instance")
    void returnCustomApiExceptionWhenCreateANewInstance() {
        ErrorType errorType = ErrorType.builder().exceptionId("ERROR_CODE").exceptionText("Error message").build();
        CustomApiException exception = new CustomApiException(errorType);

        assertSame(errorType, exception.getErrorType());
        assertEquals("Error message", exception.getMessage());
    }

}