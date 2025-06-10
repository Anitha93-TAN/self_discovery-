package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.interfaces.ISectionInterpretationService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.SectionInterpretation;
import com.self_discovery.self_discovery.selfdiscovery.repository.SectionInterpretationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionInterpretationServiceImpl implements ISectionInterpretationService {

    private final SectionInterpretationRepository repository;

    @Override
    public List<SectionInterpretationUpdateResponseDTO> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public SectionInterpretationUpdateResponseDTO getById(Long id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Interpretation not found"));
    }

    @Override
    public SectionInterpretationUpdateResponseDTO update(Long id, SectionInterpretationUpdateRequestDTO dto) {
        SectionInterpretation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interpretation not found"));

        entity.setTitle(dto.getTitle());
        entity.setMinScore(dto.getMinScore());
        entity.setMaxScore(dto.getMaxScore());
        entity.setDescription(dto.getDescription());

        return toDto(repository.save(entity));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private SectionInterpretationUpdateResponseDTO toDto(SectionInterpretation r) {
        SectionInterpretationUpdateResponseDTO dto = new SectionInterpretationUpdateResponseDTO();
        dto.setSectionInterpretationId(r.getSectionInterpretationId());
        dto.setTitle(r.getTitle());
        dto.setMinScore(r.getMinScore());
        dto.setMaxScore(r.getMaxScore());
        dto.setDescription(r.getDescription());
        return dto;
    }
}