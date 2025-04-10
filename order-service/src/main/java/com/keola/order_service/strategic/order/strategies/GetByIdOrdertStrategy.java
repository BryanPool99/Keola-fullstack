package com.keola.order_service.strategic.order.strategies;

import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.strategic.order.OrderStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
@Service
@Slf4j
@RequiredArgsConstructor
public class GetByIdOrdertStrategy implements OrderStrategy {
    @Override
    public Mono<RetrieveOrderResponse> retrieveOrder(
            Map<String, String> values, Integer limit, Integer offset,
            String sort) {
        return null;
    }
}
