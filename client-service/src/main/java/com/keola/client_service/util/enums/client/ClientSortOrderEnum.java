package com.keola.client_service.util.enums.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientSortOrderEnum {

    ID_ASC("id,asc"),
    ID_DESC("id,desc"),
    USERNAME_ASC("username,asc"),
    USERNAME_DESC("username,desc"),
    STATUS_ASC("status,asc"),
    STATUS_DESC("status,desc");

    private final String code;
}
