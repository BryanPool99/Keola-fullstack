package com.keola.client_service.util.enums.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientStrategyTypeEnum {

    LIST_ALL_CLIENTS("LAC"),
    GET_BY_ID_CLIENT("GBIC"),
    SEARCH_CLIENTS("SC");

    private final String code;
}
