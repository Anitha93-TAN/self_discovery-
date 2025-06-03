package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;

@Data
public class RecommendationResponseDTO {
    private Long recommendationId;
    private int minScore;
    private int maxScore;
    private String recommendationText;
}
