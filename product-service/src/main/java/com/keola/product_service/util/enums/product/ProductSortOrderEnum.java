package com.keola.product_service.util.enums.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSortOrderEnum {

    ID_ASC("id,asc"),
    ID_DESC("id,desc"),
    DESCRIPTION_ASC("description,asc"),
    DESCRIPTION_DESC("description,desc"),
    STATUS_ASC("status,asc"),
    STATUS_DESC("status,desc"),
    STOCK_ASC("stock,asc"),
    STOCK_DESC("stock,desc"),
    PRICE_ASC("price,asc"),
    PRICE_DESC("price,desc");

    private final String code;
}
