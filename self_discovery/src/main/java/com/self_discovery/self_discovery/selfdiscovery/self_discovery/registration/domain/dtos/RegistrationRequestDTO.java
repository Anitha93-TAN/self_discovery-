package com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {
    private String username;
    private String email;
    private String password;
    private int phoneNumber;
    private int roleId;
}
