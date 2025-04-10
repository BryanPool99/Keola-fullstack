package com.keola.client_service.strategic.client.strategies;

import com.keola.client_service.mapper.ClientMapper;
import com.keola.client_service.model.RetrieveClientResponse;
import com.keola.client_service.repository.ClientRepository;
import com.keola.client_service.strategic.client.ClientStrategy;
import com.keola.client_service.util.enums.client.ClientFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetByIdClienttStrategy implements ClientStrategy {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Mono<RetrieveClientResponse> retrieveClient(
            Map<String, String> values, Integer limit, Integer offset, String sort) {
        Integer clientId = Integer.parseInt(values.get(ClientFilterEnum.CLIENT_ID.getCode()));
        log.info("Start the strategy GetByIdClienttStrategy with clientId: {}", clientId);
        return clientRepository.findClientById(clientId)
                .map(clientMapper::toClientResponse)
                .map(clientResponse ->
                        RetrieveClientResponse.builder().clients(List.of(clientResponse)).build())
                .doOnError(error -> log.error("Error in GetByIdClienttStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of the GetByIdClienttStrategy"));

    }
}
