package com.keola.order_service.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@Builder
public class Order {

    private Integer orderId;
    private String creationDate;
    private Integer clientId;
    private List<Integer> productIds;
    private double totalPrice;

}
