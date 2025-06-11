package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler.NotFoundException;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.interfaces.ISectionInterpretationService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.SectionInterpretation;
import com.self_discovery.self_discovery.selfdiscovery.repository.SectionInterpretationRepository;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionInterpretationServiceImpl implements ISectionInterpretationService {

    private final SectionInterpretationRepository repository;

    @Override
    @Transactional
    public ApiResponse<List<SectionInterpretationUpdateResponseDTO>> getAll() {
        List<SectionInterpretationUpdateResponseDTO> list = repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatusCodes.OK, "Section interpretations fetched successfully", list);
    }

    @Override
    @Transactional
    public ApiResponse<SectionInterpretationUpdateResponseDTO> getById(Long id) {
        SectionInterpretation entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Section interpretation not found with ID: " + id));
        return new ApiResponse<>(HttpStatusCodes.OK, "Section interpretation fetched successfully", toDto(entity));
    }

    @Override
    @Transactional
    public ApiResponse<SectionInterpretationUpdateResponseDTO> update(Long id, SectionInterpretationUpdateRequestDTO dto) {
        SectionInterpretation entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Section interpretation not found with ID: " + id));

        entity.setTitle(dto.getTitle());
        entity.setMinScore(dto.getMinScore());
        entity.setMaxScore(dto.getMaxScore());
        entity.setDescription(dto.getDescription());

        SectionInterpretation updated = repository.save(entity);
        return new ApiResponse<>(HttpStatusCodes.OK, "Section interpretation updated successfully", toDto(updated));
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteAll() {
        repository.deleteAll();
        return new ApiResponse<>(HttpStatusCodes.OK, "All section interpretations deleted successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Section interpretation not found with ID: " + id);
        }
        repository.deleteById(id);
        return new ApiResponse<>(HttpStatusCodes.OK, "Section interpretation deleted successfully", null);
    }

    private SectionInterpretationUpdateResponseDTO toDto(SectionInterpretation entity) {
        SectionInterpretationUpdateResponseDTO dto = new SectionInterpretationUpdateResponseDTO();
        dto.setSectionInterpretationId(entity.getSectionInterpretationId());
        dto.setTitle(entity.getTitle());
        dto.setMinScore(entity.getMinScore());
        dto.setMaxScore(entity.getMaxScore());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
