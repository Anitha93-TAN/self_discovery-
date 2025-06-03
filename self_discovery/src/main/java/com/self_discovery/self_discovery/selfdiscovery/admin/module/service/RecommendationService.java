package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.RecommendationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.RecommendationResponseDTO;

public interface RecommendationService {
    RecommendationResponseDTO createRecommendation(RecommendationRequestDTO requestDTO);
}