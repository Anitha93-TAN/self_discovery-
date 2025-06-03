package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.InterpretationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.InterpretationResponseDTO;

public interface InterpretationService {
    InterpretationResponseDTO createInterpretation(InterpretationRequestDTO requestDTO);
}
