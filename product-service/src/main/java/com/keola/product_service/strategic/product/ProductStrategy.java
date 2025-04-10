package com.keola.product_service.strategic.product;

import com.keola.product_service.model.RetrieveProductResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ProductStrategy {

    Mono<RetrieveProductResponse> retrieveProduct(
            Map<String, String> values, Integer limit, Integer offset, String sort);
}
