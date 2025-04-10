package com.keola.product_service.util.enums.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductFilterEnum {

    PRODUCT_FILTER("description"),
    PRODUCT_ID("productId"),
    PRODUCT_RETRIEVE_TYPE("retrieveType");

    private final String code;
}
