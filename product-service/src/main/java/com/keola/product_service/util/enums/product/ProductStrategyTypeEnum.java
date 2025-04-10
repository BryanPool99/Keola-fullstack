package com.keola.product_service.util.enums.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStrategyTypeEnum {

    LIST_ALL_PRODUCTS("LAP"),
    GET_BY_ID_PRODUCT("GBIP"),
    SEARCH_PRODUCTS("SP");

    private final String code;
}
