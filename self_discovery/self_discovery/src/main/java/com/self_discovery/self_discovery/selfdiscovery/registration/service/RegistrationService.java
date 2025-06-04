package com.self_discovery.self_discovery.selfdiscovery.registration.service;


import com.self_discovery.self_discovery.selfdiscovery.registration.domain.dtos.RegistrationRequestDTO;

public interface RegistrationService {
    String registerUserOrAdmin(RegistrationRequestDTO request);
}
