package com.keola.client_service.strategic.client.strategies;

import com.keola.client_service.mapper.ClientMapper;
import com.keola.client_service.model.RetrieveClientResponse;
import com.keola.client_service.model.dto.ClientDto;
import com.keola.client_service.repository.ClientRepository;
import com.keola.client_service.strategic.client.ClientStrategy;
import com.keola.client_service.util.enums.client.ClientFilterEnum;
import com.keola.client_service.util.enums.client.ClientSortOrderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListAllClientStrategy implements ClientStrategy {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Mono<RetrieveClientResponse> retrieveClient(
            Map<String, String> values, Integer limit, Integer offset, String sort) {
        String description = values.get(ClientFilterEnum.CLIENT_FILTER.getCode());
        log.info("Start the strategy ListAllClientStrategy with description: {}", description);
        return Mono.zip(listAllClientsFromDB(description, limit, offset, sort).collectList(),
                        clientRepository.countClientListAll(description))
                .map(tuple2 -> clientMapper.toRetrieveClientResponse(tuple2.getT1().stream(),
                        limit, offset, tuple2.getT2()))
                .doOnError(error -> log.error("Error in ListAllClientStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of the ListAllClientStrategy"));
    }

    private Flux<ClientDto> listAllClientsFromDB(String filter, Integer limit, Integer offset, String sort) {
        if (sort.isBlank()) {
            return clientRepository.findListAllClientIdAsc(filter, limit, offset);
        }
        Map<String, Function<String, Flux<ClientDto>>> queries = Map.ofEntries(
                Map.entry(ClientSortOrderEnum.ID_ASC.getCode(), field ->
                        clientRepository.findListAllClientIdAsc(filter, limit, offset)),
                Map.entry(ClientSortOrderEnum.ID_DESC.getCode(), field ->
                        clientRepository.findListAllClientIdDesc(filter, limit, offset)),
                Map.entry(ClientSortOrderEnum.USERNAME_ASC.getCode(), field ->
                        clientRepository.findListAllClientUsernameAsc(filter, limit, offset)),
                Map.entry(ClientSortOrderEnum.USERNAME_DESC.getCode(), field ->
                        clientRepository.findListAllClientUsernameDesc(filter, limit, offset))
        );
        return queries.getOrDefault(sort, field -> Flux.defer(() ->
                Flux.error(new RuntimeException("Bad parameters")))).apply(sort);
    }
}
