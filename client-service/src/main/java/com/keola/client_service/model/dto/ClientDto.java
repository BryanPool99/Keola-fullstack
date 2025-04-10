package com.keola.client_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDto {

    private Integer clientid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String mobile;
    private String dni;
    private Integer state;
}
