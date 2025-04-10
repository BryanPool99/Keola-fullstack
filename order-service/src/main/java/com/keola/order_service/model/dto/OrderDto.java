package com.keola.order_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderDto {

    private Integer orderId;
    private String creationDate;
    private String client;
    private Integer status;
    private BigDecimal totalPrice;

}
