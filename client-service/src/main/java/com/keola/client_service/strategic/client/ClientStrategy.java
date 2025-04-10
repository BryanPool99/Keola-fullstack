package com.keola.client_service.strategic.client;

import com.keola.client_service.model.RetrieveClientResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ClientStrategy {

    Mono<RetrieveClientResponse> retrieveClient(
            Map<String, String> values, Integer limit, Integer offset, String sort);

}
