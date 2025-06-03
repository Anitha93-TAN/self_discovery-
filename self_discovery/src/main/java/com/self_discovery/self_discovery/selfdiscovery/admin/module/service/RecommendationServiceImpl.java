package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.RecommendationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.RecommendationResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Recommendation;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.RecommendationRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private TestRepository testRepository;

    @Override
    public RecommendationResponseDTO createRecommendation(RecommendationRequestDTO requestDTO) {
        Optional<Test> optionalTest = testRepository.findById(requestDTO.getTestId());
        if (optionalTest.isEmpty()) {
            throw new RuntimeException("Test not found with ID: " + requestDTO.getTestId());
        }

        Recommendation recommendation = new Recommendation();
        recommendation.setTest(optionalTest.get());
        recommendation.setMinScore(requestDTO.getMinScore());
        recommendation.setMaxScore(requestDTO.getMaxScore());
        recommendation.setRecommendationText(requestDTO.getRecommendationText());

        Recommendation saved = recommendationRepository.save(recommendation);

        return new RecommendationResponseDTO(
                saved.getRecommendationId(),
                saved.getTest().getTestId(),
                saved.getTest().getTitle(),
                saved.getMinScore(),
                saved.getMaxScore(),
                saved.getRecommendationText()
        );
    }
}