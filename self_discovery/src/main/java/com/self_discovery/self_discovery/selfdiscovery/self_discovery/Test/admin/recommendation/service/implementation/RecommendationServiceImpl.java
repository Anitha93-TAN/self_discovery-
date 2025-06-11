package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.interfaces.IRecommendationService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Recommendation;
import com.self_discovery.self_discovery.selfdiscovery.repository.RecommendationRepository;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationServiceImpl implements IRecommendationService {

    @Autowired
    private RecommendationRepository repository;

    @Override
    @Transactional
    public ApiResponse<List<RecommendationUpdateResponseDTO>> getAll() {
        List<RecommendationUpdateResponseDTO> list = repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatusCodes.OK, "Recommendations fetched successfully", list);
    }

    @Override
    @Transactional
    public ApiResponse<RecommendationUpdateResponseDTO> getById(Long id) {
        Recommendation entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recommendation not found with ID: " + id));
        return new ApiResponse<>(HttpStatusCodes.OK, "Recommendation fetched successfully", toDto(entity));
    }

    @Override
    @Transactional
    public ApiResponse<RecommendationUpdateResponseDTO> update(Long id, RecommendationUpdateRequestDTO dto) {
        Recommendation entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recommendation not found with ID: " + id));

        entity.setTitle(dto.getTitle());
        entity.setMinScore(dto.getMinScore());
        entity.setMaxScore(dto.getMaxScore());
        entity.setRecommendationText(dto.getRecommendationText());

        Recommendation updated = repository.save(entity);
        return new ApiResponse<>(HttpStatusCodes.OK, "Recommendation updated successfully", toDto(updated));
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteAll() {
        repository.deleteAll();
        return new ApiResponse<>(HttpStatusCodes.OK, "All recommendations deleted successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Recommendation not found with ID: " + id);
        }
        repository.deleteById(id);
        return new ApiResponse<>(HttpStatusCodes.OK, "Recommendation deleted successfully", null);
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