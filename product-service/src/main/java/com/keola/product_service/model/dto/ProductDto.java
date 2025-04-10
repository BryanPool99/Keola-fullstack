package com.keola.product_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDto {

    private Integer productid;
    private String description;
    private Integer state;
    private Integer stock;
    private BigDecimal price;
}
