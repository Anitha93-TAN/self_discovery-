package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface IInterpretationService {
    ApiResponse<List<InterpretationUpdateResponseDTO>> getAll();
    ApiResponse<InterpretationUpdateResponseDTO> getById(Long id);
    ApiResponse<InterpretationUpdateResponseDTO> update(Long id, InterpretationUpdateRequestDTO dto);
    ApiResponse<Void> deleteAll();
    ApiResponse<Void> deleteById(Long id);
}
