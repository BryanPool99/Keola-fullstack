package com.keola.order_service.service.impl;

import com.keola.order_service.model.CreateOrderRequest;
import com.keola.order_service.model.RetrieveOrderDetailResponse;
import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.model.UpdateOrderRequest;
import com.keola.order_service.model.dto.ClientDto;
import com.keola.order_service.model.dto.ProductDto;
import com.keola.order_service.service.OrderService;
import com.keola.order_service.strategic.order.OrderStrategyFactory;
import com.keola.order_service.util.Util;
import com.keola.order_service.util.enums.order.OrderFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderStrategyFactory orderStrategyFactory;

    @Override
    public Mono<RetrieveOrderResponse> listOrders(
            String filter, Integer limit, Integer offset, String sort, String user) {
        log.info("Start the method listOrders: sort: {}, filter:{} , limit: {}, offset: {}",
                sort, filter, limit, offset);
        Map<String, String> values = Util.parseStringToMap(filter);
        String retrieveType = values.get(OrderFilterEnum.ORDER_RETRIEVE_TYPE.getCode());

        return orderStrategyFactory.getStrategy(retrieveType)
                .retrieveOrder(values, limit, offset, sort)
                .doOnError(error -> log.error("Error in listClients: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of listClients"));
    }

    @Override
    public Mono<ClientDto> getClientDetails(String filter) {
        return null;
    }

    @Override
    public Mono<ProductDto> getProductDetails(String filter, Integer limit, Integer offset, String sort) {
        return null;
    }

    @Override
    public Mono<RetrieveOrderDetailResponse> getOrderDetail(
            Integer orderId, String filter, Integer limit, Integer offset, String sort, String user) {
        return null;
    }

    @Override
    public Mono<Void> createOrder(CreateOrderRequest createOrderRequest, String user) {
        return null;
    }

    @Override
    public Mono<Void> updateOrder(UpdateOrderRequest updateOrderRequest, String user) {
        return null;
    }

    @Override
    public Mono<Void> deleteOrder(Integer orderId, String user) {
        return null;
    }
}
