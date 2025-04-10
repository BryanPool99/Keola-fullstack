package com.keola.client_service.util.enums.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientFilterEnum {

    CLIENT_FILTER("description"),
    CLIENT_ID("clientId"),
    CLIENT_RETRIEVE_TYPE("retrieveType");

    private final String code;
}
