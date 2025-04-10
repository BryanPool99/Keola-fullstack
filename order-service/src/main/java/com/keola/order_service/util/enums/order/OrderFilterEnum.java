package com.keola.order_service.util.enums.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderFilterEnum {

    ORDER_FILTER("description"),
    ORDER_ID("orderId"),
    ORDER_RETRIEVE_TYPE("retrieveType");

    private final String code;
}
