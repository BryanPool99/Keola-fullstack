package com.keola.order_service.service;

import com.keola.order_service.model.CreateOrderRequest;
import com.keola.order_service.model.RetrieveOrderDetailResponse;
import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.model.UpdateOrderRequest;
import com.keola.order_service.model.dto.ClientDto;
import com.keola.order_service.model.dto.ProductDto;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<RetrieveOrderResponse> listOrders(String filter, Integer limit, Integer offset, String sort, String user);

    Mono<RetrieveOrderDetailResponse> getOrderDetail(
            Integer orderId, String filter, Integer limit, Integer offset, String sort, String user);

    Mono<ClientDto> getClientDetails(String filter);

    Mono<ProductDto> getProductDetails(String filter, Integer limit, Integer offset, String sort);

    Mono<Void> deleteOrder(Integer orderId, String user);

    Mono<Void> createOrder(CreateOrderRequest createOrderRequest, String user);

    Mono<Void> updateOrder(UpdateOrderRequest updateOrderRequest, String user);

}
