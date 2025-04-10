package com.keola.product_service.util;

import com.keola.product_service.util.enums.product.ProductStrategyTypeEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UtilTest {

    @Test
    @DisplayName("Return a map with two elements when input has correct format")
    void returnAMapWithTwoElementsWhenInputHasCorrectFormat() {
        String input = "key1:value1,key2:value2";
        Map<String, String> result = Util.parseStringToMap(input);
        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }

    @Test
    @DisplayName("Return an empty map when input is empty")
    void returnAnEmptyMapWhenInputIsEmpty() {
        String input = "";
        Map<String, String> result = Util.parseStringToMap(input);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Return a map with one element when input contains one pair")
    void returnAMapWithOneElementWhenInputContainsOnePair() {
        String input = "key1:value1";
        Map<String, String> result = Util.parseStringToMap(input);
        assertEquals(1, result.size());
        assertEquals("value1", result.get("key1"));
    }

    @Test
    @DisplayName("Return an empty map when input contains a single empty key-value pair")
    void returnAnEmptyMapWhenInputContainsEmptyKeyValuePair() {
        String input = "key1: , key2: ";
        Map<String, String> result = Util.parseStringToMap(input);
        assertTrue(!result.isEmpty());
    }

    @Test
    @DisplayName("Handle input with null or empty strings in values")
    void handleInputWithNullOrEmptyStringsInValues() {
        String input = "key1:,key2:value2,key3:";
        Map<String, String> result = Util.parseStringToMap(input);
        assertEquals(3, result.size());
        assertEquals("value2", result.get("key2"));
    }

    @Test
    @DisplayName("Return an empty map when input contains invalid format")
    void returnAnEmptyMapWhenInputContainsInvalidFormat() {
        String input = "key1_value1,key2:value2";
        Map<String, String> result = Util.parseStringToMap(input);
        assertTrue(!result.isEmpty());
    }


    @Test
    @DisplayName("Return a strategic map when is user")
    void returnAStrategicMapWhenIsUser() {
        Map<String, ProductStrategyTypeEnum> result = Util.getProductStrategic();
        assertEquals(ProductStrategyTypeEnum.values().length, result.size());
        for (ProductStrategyTypeEnum type : ProductStrategyTypeEnum.values()) {
            assertEquals(type, result.get(type.getCode()));
        }
    }
}