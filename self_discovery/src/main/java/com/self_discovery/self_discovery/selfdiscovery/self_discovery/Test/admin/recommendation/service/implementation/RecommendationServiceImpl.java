package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.interfaces.IRecommendationService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Recommendation;
import com.self_discovery.self_discovery.selfdiscovery.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements IRecommendationService {

    private final RecommendationRepository repository;

    @Override
    public List<RecommendationUpdateResponseDTO> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public RecommendationUpdateResponseDTO getById(Long id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Recommendation not found"));
    }

    @Override
    public RecommendationUpdateResponseDTO update(Long id, RecommendationUpdateRequestDTO dto) {
        Recommendation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found"));

        entity.setTitle(dto.getTitle());
        entity.setMinScore(dto.getMinScore());
        entity.setMaxScore(dto.getMaxScore());
        entity.setRecommendationText(dto.getRecommendationText());

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

    private RecommendationUpdateResponseDTO toDto(Recommendation r) {
        RecommendationUpdateResponseDTO dto = new RecommendationUpdateResponseDTO();
        dto.setRecommendationId(r.getRecommendationId());
        dto.setTitle(r.getTitle());
        dto.setMinScore(r.getMinScore());
        dto.setMaxScore(r.getMaxScore());
        dto.setRecommendationText(r.getRecommendationText());
        return dto;
    }
}
