package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import lombok.Data;

@Data
public class RecommendationResponseDTO {
    private Long recommendationId;
    private String title;
    private int minScore;
    private int maxScore;
    private String recommendationText;
}
