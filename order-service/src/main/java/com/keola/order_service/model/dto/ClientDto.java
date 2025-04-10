package com.keola.order_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;

}
