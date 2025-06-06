package com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.service;


import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.dtos.RegistrationRequestDTO;

public interface RegistrationService {
    String registerUserOrAdmin(RegistrationRequestDTO request);
}
