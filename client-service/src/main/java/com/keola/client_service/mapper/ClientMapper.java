package com.keola.client_service.mapper;

import com.keola.client_service.model.ClientResponse;
import com.keola.client_service.model.MetadataResponse;
import com.keola.client_service.model.RetrieveClientResponse;
import com.keola.client_service.model.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "clients", source = "clients")
    @Mapping(target = "metadata", expression = "java(toMetadataResponse(limit, offset, totalElements))")
    RetrieveClientResponse toRetrieveClientResponse(
            Stream<ClientDto> clients, Integer limit, Integer offset, long totalElements);

    MetadataResponse toMetadataResponse(Integer limit, Integer offset, long totalElements);

    @Mapping(target = "id", source = "clientid")
    @Mapping(target = "firstName", source = "firstname")
    @Mapping(target = "lastName", source = "lastname")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "mobile", source = "mobile")
    @Mapping(target = "dni", source = "dni")
    @Mapping(target = "status", source = "state")
    ClientResponse toClientResponse(ClientDto clientDto);

    List<ClientResponse> toListClientResponse(Stream<ClientDto> clientSearchDtos);

}
