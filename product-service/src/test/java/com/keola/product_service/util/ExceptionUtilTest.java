package com.keola.product_service.util;

import com.keola.product_service.model.ErrorType;
import com.keola.product_service.util.constants.Constants;
import com.keola.product_service.util.enums.ErrorEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExceptionUtilTest {

    @Test
    @DisplayName("Return a ErrorType object when buildGenesisException is called")
    void returnAErrorTypeObjectWhenBuildGenesisExceptionIsCalled() {

        ErrorEnum errorEnum = ErrorEnum.BAD_PARAMETERS;
        ErrorType errorType = ExceptionUtil.buildGenesisException(errorEnum);

        assertEquals(errorEnum.getCategory().getExceptionId(), errorType.getExceptionId());
        assertEquals(Constants.CUSTOM_EXCEPTION_TEXT, errorType.getExceptionText());
        assertEquals(Constants.MORE_INFO, errorType.getMoreInfo());
        assertEquals(errorEnum.getCategory().getHttpStatus(), errorType.getHttpStatus());
        assertEquals(1, errorType.getExceptionDetails().size());
        assertEquals(errorEnum.getCode(), errorType.getExceptionDetails().get(0).getCode());
        assertEquals(Constants.MICROSERVICE, errorType.getExceptionDetails().get(0).getComponent());
        assertEquals(errorEnum.getDetail(), errorType.getExceptionDetails().get(0).getDescription());
    }

    @Test
    @DisplayName("Return a ErrorType object when buildGenesisException is called with a null ErrorEnum")
    void returnAErrorTypeObjectWhenBuildGenesisExceptionIsCalledWithANullErrorEnum() {

        ErrorEnum errorEnum = ErrorEnum.BAD_PARAMETERS;

        Mono<Object> mono = ExceptionUtil.buildGenesisExceptionMono(errorEnum);

        StepVerifier.create(mono)
                .expectErrorMatches(throwable -> throwable instanceof CustomApiException &&
                        ((CustomApiException) throwable).getErrorType().getExceptionId().equals(errorEnum.getCategory().getExceptionId()))
                .verify();
    }

    @Test
    @DisplayName("Return a ErrorType object when buildGenesisExceptionFlux is called with a null ErrorEnum")
    void returnAErrorTypeObjectWhenBuildGenesisExceptionFluxIsCalledWithANullErrorEnum() {
        ErrorEnum errorEnum = ErrorEnum.BAD_PARAMETERS;

        Flux<Object> flux = ExceptionUtil.buildGenesisExceptionFlux(errorEnum);

        StepVerifier.create(flux)
                .expectErrorMatches(throwable -> throwable instanceof CustomApiException &&
                        ((CustomApiException) throwable).getErrorType().getExceptionId().equals(errorEnum.getCategory().getExceptionId()))
                .verify();
    }

}