package com.keola.client_service.service.impl;

import com.keola.client_service.model.CreateClientRequest;
import com.keola.client_service.model.RetrieveClientResponse;
import com.keola.client_service.model.UpdateClientRequest;
import com.keola.client_service.model.entity.ClientEntity;
import com.keola.client_service.repository.ClientRepository;
import com.keola.client_service.service.ClientService;
import com.keola.client_service.strategic.client.ClientStrategyFactory;
import com.keola.client_service.util.Util;
import com.keola.client_service.util.enums.ErrorEnum;
import com.keola.client_service.util.enums.client.ClientFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.keola.client_service.util.ExceptionUtil.buildGenesisExceptionMono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientStrategyFactory clientStrategyFactory;

    private final ClientRepository clientRepository;

    private final TransactionalOperator transactionalOperator;

    @Override
    public Mono<RetrieveClientResponse> listClients(
            String filter, Integer limit, Integer offset, String sort, String user) {
        log.info("Start the method listClients: sort: {}, filter:{} , limit: {}, offset: {}", sort, filter, limit,
                offset);
        Map<String, String> values = Util.parseStringToMap(filter);
        String retrieveType = values.get(ClientFilterEnum.CLIENT_RETRIEVE_TYPE.getCode());
        return clientStrategyFactory.getStrategy(retrieveType)
                .retrieveClient(values, limit, offset, sort)
                .doOnError(error -> log.error("Error in listClients: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of listClients"));
    }

    @Override
    public Mono<Void> createClient(CreateClientRequest createClientRequest, String user) {
        log.info("Start the createProduct by user :{}", user);
        return createClientEntity(createClientRequest, user)
                .then()
                .as(transactionalOperator::transactional)
                .doOnError(error -> log.error("Error during createProduct process", error))
                .doOnSuccess(success -> log.info("Product created successfully"));

    }

    private Mono<ClientEntity> createClientEntity(CreateClientRequest createClientRequest, String user) {
        var client = ClientEntity.builder()
                .firstName(createClientRequest.getFirstName())
                .lastName(createClientRequest.getLastName())
                .userAlias(createClientRequest.getUsername())
                .email(createClientRequest.getEmail())
                .phone(createClientRequest.getPhone())
                .mobile(createClientRequest.getMobile())
                .identityCard(createClientRequest.getDni())
                .createdBy(user)
                .creationDate(LocalDateTime.now())
                .build();
        return clientRepository.save(client);
    }

    @Override
    public Mono<Void> updateClient(UpdateClientRequest updateClientRequest, String user) {
        log.info("Start the updateProduct by user : {}", user);
        return clientRepository.findByClientId(updateClientRequest.getId())
                .switchIfEmpty(Mono.defer(() -> buildGenesisExceptionMono(ErrorEnum.CLIENT_ID_NOT_EXISTS)))
                .flatMap(clientEntity -> updateClientEntity(clientEntity, updateClientRequest, user))
                .then()
                .as(transactionalOperator::transactional)
                .doOnError(error -> log.error("Error during updateProduct process", error))
                .doOnSuccess(success -> log.info("Product updated successfully"));

    }

    private Mono<ClientEntity> updateClientEntity(
            ClientEntity clientEntity, UpdateClientRequest updateClientRequest, String user) {
        log.info("Start the updateClientEntity by user : {}", user);

        Optional.ofNullable(updateClientRequest.getFirstName())
                .ifPresent(clientEntity::setFirstName);
        Optional.ofNullable(updateClientRequest.getLastName())
                .ifPresent(clientEntity::setLastName);
        Optional.ofNullable(updateClientRequest.getUsername())
                .ifPresent(clientEntity::setUserAlias);
        Optional.ofNullable(updateClientRequest.getEmail())
                .ifPresent(clientEntity::setEmail);
        Optional.ofNullable(updateClientRequest.getPhone())
                .ifPresent(clientEntity::setPhone);
        Optional.ofNullable(updateClientRequest.getMobile())
                .ifPresent(clientEntity::setMobile);
        Optional.ofNullable(updateClientRequest.getDni())
                .ifPresent(clientEntity::setIdentityCard);
        clientEntity.setUpdatedBy(user);
        clientEntity.setUpdateDate(LocalDateTime.now());

        return clientRepository.save(clientEntity);
    }
}
