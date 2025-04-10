package com.keola.product_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductServiceApplication productServiceApplication;

    @Test
    void contextLoads() {
        assertThat(productServiceApplication).isNotNull();
    }

}
