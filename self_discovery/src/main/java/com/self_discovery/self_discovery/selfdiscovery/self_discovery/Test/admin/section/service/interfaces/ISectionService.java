package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface ISectionService {
    ApiResponse<List<SectionUpdateResponseDTO>> getAllSections();
    ApiResponse<SectionUpdateResponseDTO> getSectionById(Long id);
    ApiResponse<SectionUpdateResponseDTO> updateSection(Long id, SectionUpdateRequestDTO dto);
    ApiResponse<Void> deleteAllSections();
    ApiResponse<Void> deleteSectionById(Long id);
}