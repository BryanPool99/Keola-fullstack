package com.keola.order_service.strategic.order.strategies;

import com.keola.order_service.mapper.OrderMapper;
import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.repository.OrderRepository;
import com.keola.order_service.strategic.order.OrderStrategy;
import com.keola.order_service.util.enums.order.OrderFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListAllOrderStrategy implements OrderStrategy {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Mono<RetrieveOrderResponse> retrieveOrder(
            Map<String, String> values, Integer limit, Integer offset,
            String sort) {
        String description = values.get(OrderFilterEnum.ORDER_FILTER.getCode());
        log.info("Start the strategy ListAllOrderStrategy with description: {}", description);

        return null;
    }
}
