package com.keola.order_service.util.enums.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStrategyTypeEnum {

    LIST_ALL_ORDERS("LAO"),
    GET_BY_ID_ORDER("GBIO"),
    SEARCH_ORDERS("SO");

    private final String code;

}
