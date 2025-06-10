package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.interfaces.IInterpretationService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Interpretation;
import com.self_discovery.self_discovery.selfdiscovery.repository.InterpretationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterpretationServiceImpl implements IInterpretationService {

    private final InterpretationRepository repository;

    @Override
    public List<InterpretationUpdateResponseDTO> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public InterpretationUpdateResponseDTO getById(Long id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Interpretation not found"));
    }

    @Override
    public InterpretationUpdateResponseDTO update(Long id, InterpretationUpdateRequestDTO dto) {
        Interpretation entity = repository.findById(id)
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
