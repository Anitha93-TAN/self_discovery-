package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos;

import lombok.Data;

@Data
public class RecommendationUpdateRequestDTO {
    private Long recommendationId;
    private String title;
    private int minScore;
    private int maxScore;
    private String recommendationText;
}

