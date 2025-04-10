package com.keola.order_service.util.enums.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSortOrderEnum {

    ID_ASC("orderId,asc"),
    ID_DESC("orderId,desc");

    private final String code;
}
