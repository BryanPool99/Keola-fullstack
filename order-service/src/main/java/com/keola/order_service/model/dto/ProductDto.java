package com.keola.order_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String description;
    private int quantity;
    private double price;

}
