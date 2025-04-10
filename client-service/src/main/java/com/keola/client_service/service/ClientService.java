package com.keola.client_service.service;

import com.keola.client_service.model.CreateClientRequest;
import com.keola.client_service.model.RetrieveClientResponse;
import com.keola.client_service.model.UpdateClientRequest;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<RetrieveClientResponse> listClients(String filter, Integer limit, Integer offset, String sort, String user);

    Mono<Void> createClient(CreateClientRequest createClientRequest, String user);

    Mono<Void> updateClient(UpdateClientRequest updateClientRequest, String user);

}
