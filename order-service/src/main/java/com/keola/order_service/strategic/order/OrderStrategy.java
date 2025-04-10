package com.keola.order_service.strategic.order;

import com.keola.order_service.model.RetrieveOrderResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface OrderStrategy {

    Mono<RetrieveOrderResponse> retrieveOrder(
            Map<String, String> values, Integer limit, Integer offset, String sort);

}
