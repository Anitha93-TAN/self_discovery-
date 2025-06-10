package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos.*;
import java.util.List;

public interface ISectionInterpretationService {
    List<SectionInterpretationUpdateResponseDTO> getAll();
    SectionInterpretationUpdateResponseDTO getById(Long id);
    SectionInterpretationUpdateResponseDTO update(Long id, SectionInterpretationUpdateRequestDTO dto);
    void deleteAll();
    void deleteById(Long id);
}