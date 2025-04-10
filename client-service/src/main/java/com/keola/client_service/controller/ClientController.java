package com.keola.client_service.controller;

import com.keola.client_service.api.ClientApi;
import com.keola.client_service.model.CreateClientRequest;
import com.keola.client_service.model.RetrieveClientResponse;
import com.keola.client_service.model.UpdateClientRequest;
import com.keola.client_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/serviceclient/v1")
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Override
    public Mono<ResponseEntity<Void>> createClient(
            String unICAUser, Mono<CreateClientRequest> createClientRequest,
            ServerWebExchange exchange) {
        return createClientRequest.flatMap(request ->
                        clientService.createClient(request, unICAUser))
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Override
    public Mono<ResponseEntity<RetrieveClientResponse>> retrieveClient(
            String unICAUser, String filter, String sort,
            Integer limit, Integer offset,
            ServerWebExchange exchange) {
        return clientService.listClients(filter, limit, offset, sort, unICAUser)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> updateClient(
            String unICAUser, Mono<UpdateClientRequest> updateClientRequest,
            ServerWebExchange exchange) {
        return updateClientRequest.flatMap(request ->
                        clientService.updateClient(request, unICAUser))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
