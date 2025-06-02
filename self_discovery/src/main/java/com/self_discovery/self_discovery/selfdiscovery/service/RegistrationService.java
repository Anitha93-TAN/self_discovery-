package com.self_discovery.self_discovery.selfdiscovery.service;


import com.self_discovery.self_discovery.selfdiscovery.domain.dtos.RegistrationRequestDTO;

public interface RegistrationService {
    String registerUserOrAdmin(RegistrationRequestDTO request);
}
