package com.keola.order_service.controller;

import com.keola.order_service.api.OrderApi;
import com.keola.order_service.model.CreateOrderRequest;
import com.keola.order_service.model.RetrieveOrderDetailResponse;
import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.model.UpdateOrderRequest;
import com.keola.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/serviceorder/v1")
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public Mono<ResponseEntity<Void>> createOrder(
            String unICAUser, Mono<CreateOrderRequest> createOrderRequest, ServerWebExchange exchange) {
        return createOrderRequest.flatMap(request ->
                        orderService.createOrder(request, unICAUser))
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteOrder(String unICAUser, Integer orderId, ServerWebExchange exchange) {
        return orderService.deleteOrder(orderId, unICAUser)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @Override
    public Mono<ResponseEntity<RetrieveOrderResponse>> retrieveOrder(
            String unICAUser, String filter, String sort, Integer limit, Integer offset, ServerWebExchange exchange) {
        return orderService.listOrders(filter, limit, offset, sort, unICAUser)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<RetrieveOrderDetailResponse>> retrieveOrderDetail(
            String unICAUser, Integer orderId, String filter, String sort, Integer limit, Integer offset,
            ServerWebExchange exchange) {

        return orderService.getOrderDetail(orderId, filter, limit, offset, sort, unICAUser)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> updateOrder(
            String unICAUser, Mono<UpdateOrderRequest> updateOrderRequest, ServerWebExchange exchange) {
        return updateOrderRequest.flatMap(request ->
                        orderService.updateOrder(request, unICAUser))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
