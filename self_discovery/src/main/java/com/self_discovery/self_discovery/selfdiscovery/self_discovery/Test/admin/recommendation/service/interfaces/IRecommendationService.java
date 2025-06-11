package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface IRecommendationService {
    ApiResponse<List<RecommendationUpdateResponseDTO>> getAll();
    ApiResponse<RecommendationUpdateResponseDTO> getById(Long id);
    ApiResponse<RecommendationUpdateResponseDTO> update(Long id, RecommendationUpdateRequestDTO dto);
    ApiResponse<Void> deleteAll();
    ApiResponse<Void> deleteById(Long id);
}
