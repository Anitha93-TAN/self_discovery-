package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.interfaces.IInterpretationService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Interpretation;
import com.self_discovery.self_discovery.selfdiscovery.repository.InterpretationRepository;
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
public class InterpretationServiceImpl implements IInterpretationService {

    private final InterpretationRepository repository;

    @Override
    @Transactional
    public ApiResponse<List<InterpretationUpdateResponseDTO>> getAll() {
        List<InterpretationUpdateResponseDTO> list = repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatusCodes.OK, "Interpretations fetched successfully", list);
    }

    @Override
    @Transactional
    public ApiResponse<InterpretationUpdateResponseDTO> getById(Long id) {
        Interpretation interpretation = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interpretation not found with ID: " + id));
        return new ApiResponse<>(HttpStatusCodes.OK, "Interpretation fetched successfully", toDto(interpretation));
    }

    @Override
    @Transactional
    public ApiResponse<InterpretationUpdateResponseDTO> update(Long id, InterpretationUpdateRequestDTO dto) {
        Interpretation entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Interpretation not found with ID: " + id));

        entity.setTitle(dto.getTitle());
        entity.setMinScore(dto.getMinScore());
        entity.setMaxScore(dto.getMaxScore());
        entity.setDescription(dto.getDescription());

        Interpretation updated = repository.save(entity);
        return new ApiResponse<>(HttpStatusCodes.OK, "Interpretation updated successfully", toDto(updated));
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteAll() {
        repository.deleteAll();
        return new ApiResponse<>(HttpStatusCodes.OK, "All interpretations deleted successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Interpretation not found with ID: " + id);
        }
        repository.deleteById(id);
        return new ApiResponse<>(HttpStatusCodes.OK, "Interpretation deleted successfully", null);
    }

    private InterpretationUpdateResponseDTO toDto(Interpretation entity) {
        InterpretationUpdateResponseDTO dto = new InterpretationUpdateResponseDTO();
        dto.setInterpretationId(entity.getInterpretationId());
        dto.setTitle(entity.getTitle());
        dto.setMinScore(entity.getMinScore());
        dto.setMaxScore(entity.getMaxScore());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}
