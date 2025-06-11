package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface ISectionInterpretationService {
    ApiResponse<List<SectionInterpretationUpdateResponseDTO>> getAll();
    ApiResponse<SectionInterpretationUpdateResponseDTO> getById(Long id);
    ApiResponse<SectionInterpretationUpdateResponseDTO> update(Long id, SectionInterpretationUpdateRequestDTO dto);
    ApiResponse<Void> deleteAll();
    ApiResponse<Void> deleteById(Long id);
}
