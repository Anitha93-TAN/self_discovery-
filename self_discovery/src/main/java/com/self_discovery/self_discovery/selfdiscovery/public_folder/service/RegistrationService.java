package com.self_discovery.self_discovery.selfdiscovery.public_folder.service;


import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.dtos.RegistrationRequestDTO;

public interface RegistrationService {
    String registerUserOrAdmin(RegistrationRequestDTO request);
}
