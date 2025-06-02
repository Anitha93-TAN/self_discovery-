package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionInterpretationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionInterpretationResponseDTO;

public interface SectionInterpretationService {
    SectionInterpretationResponseDTO createSectionInterpretation(SectionInterpretationRequestDTO requestDTO);
}
