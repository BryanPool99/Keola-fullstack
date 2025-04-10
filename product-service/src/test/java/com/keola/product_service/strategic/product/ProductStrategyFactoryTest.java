package com.keola.product_service.strategic.product;

import com.keola.product_service.strategic.product.strategies.ListAllProductStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductStrategyFactoryTest {

    @Mock
    ListAllProductStrategy productStrategy;

    @InjectMocks
    ProductStrategyFactory strategyFactory;

    @Test
    @DisplayName("Return strategy when send type")
    void returnStrategyWhenSendType() {

        ProductStrategy result = strategyFactory.getStrategy("LAP");

        Assertions.assertEquals(productStrategy, result);
    }

}