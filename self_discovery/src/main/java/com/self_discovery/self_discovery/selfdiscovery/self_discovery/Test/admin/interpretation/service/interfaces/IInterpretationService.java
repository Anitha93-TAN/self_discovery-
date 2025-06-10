package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.dtos.*;
import java.util.List;

public interface IInterpretationService {
    List<InterpretationUpdateResponseDTO> getAll();
    InterpretationUpdateResponseDTO getById(Long id);
    InterpretationUpdateResponseDTO update(Long id, InterpretationUpdateRequestDTO dto);
    void deleteAll();
    void deleteById(Long id);
}