package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service;

import com.self_discovery.self_discovery.selfdiscovery.repository.SectionRepository;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements ISectionService {

    private final SectionRepository sectionRepository;

    @Override
    public List<SectionUpdateResponseDTO> getAllSections() {
        return sectionRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SectionUpdateResponseDTO getSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return toDTO(section);
    }

    @Override
    public SectionUpdateResponseDTO updateSection(Long id, SectionUpdateRequestDTO dto) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));

        section.setSectionTitle(dto.getSectionTitle());
        section.setSectionOrder(dto.getSectionOrder());
        section.setRandomizeQuestions(dto.isRandomizeQuestions());

        return toDTO(sectionRepository.save(section));
    }

    @Override
    public void deleteAllSections() {
        sectionRepository.deleteAll();
    }

    @Override
    public void deleteSectionById(Long id) {
        sectionRepository.deleteById(id);
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