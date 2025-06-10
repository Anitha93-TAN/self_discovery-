package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos.*;
import java.util.List;

public interface IRecommendationService {
    List<RecommendationUpdateResponseDTO> getAll();
    RecommendationUpdateResponseDTO getById(Long id);
    RecommendationUpdateResponseDTO update(Long id, RecommendationUpdateRequestDTO dto);
    void deleteAll();
    void deleteById(Long id);
}