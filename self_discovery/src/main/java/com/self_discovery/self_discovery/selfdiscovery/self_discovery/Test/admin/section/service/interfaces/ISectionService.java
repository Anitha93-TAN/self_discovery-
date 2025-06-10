package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.*;
import java.util.List;

public interface ISectionService {
    List<SectionUpdateResponseDTO> getAllSections();
    SectionUpdateResponseDTO getSectionById(Long id);
    SectionUpdateResponseDTO updateSection(Long id, SectionUpdateRequestDTO dto);
    void deleteAllSections();
    void deleteSectionById(Long id);
}