package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.repository.SectionRepository;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service.interfaces.ISectionService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class SectionServiceImpl implements ISectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    @Transactional
    public ApiResponse<List<SectionUpdateResponseDTO>> getAllSections() {
        List<SectionUpdateResponseDTO> list = sectionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatusCodes.OK, "Sections fetched successfully", list);
    }

    @Override
    @Transactional
    public ApiResponse<SectionUpdateResponseDTO> getSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Section not found with ID: " + id));
        SectionUpdateResponseDTO dto = toDTO(section);
        return new ApiResponse<>(HttpStatusCodes.OK, "Section fetched successfully", dto);
    }

    @Override
    @Transactional
    public ApiResponse<SectionUpdateResponseDTO> updateSection(Long id, SectionUpdateRequestDTO dto) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Section not found with ID: " + id));

        section.setSectionTitle(dto.getSectionTitle());
        section.setSectionOrder(dto.getSectionOrder());
        section.setRandomizeQuestions(dto.isRandomizeQuestions());

        Section updated = sectionRepository.save(section);
        SectionUpdateResponseDTO responseDTO = toDTO(updated);
        return new ApiResponse<>(HttpStatusCodes.OK, "Section updated successfully", responseDTO);
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteAllSections() {
        sectionRepository.deleteAll();
        return new ApiResponse<>(HttpStatusCodes.OK, "All sections deleted successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteSectionById(Long id) {
        if (!sectionRepository.existsById(id)) {
            throw new NoSuchElementException("Section not found with ID: " + id);
        }
        sectionRepository.deleteById(id);
        return new ApiResponse<>(HttpStatusCodes.OK, "Section deleted successfully", null);
    }

    private SectionUpdateResponseDTO toDTO(Section section) {
        SectionUpdateResponseDTO dto = new SectionUpdateResponseDTO();
        dto.setSectionId(section.getSectionId());
        dto.setSectionTitle(section.getSectionTitle());
        dto.setSectionOrder(section.getSectionOrder());
        dto.setRandomizeQuestions(section.isRandomizeQuestions());
        return dto;
    }
}
