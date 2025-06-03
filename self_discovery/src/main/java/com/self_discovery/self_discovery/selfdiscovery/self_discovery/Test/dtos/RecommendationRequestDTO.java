package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.dtos;

import lombok.Data;

@Data
public class RecommendationRequestDTO {
   // private Long recommendationId;
    private int minScore;
    private int maxScore;
    private String recommendationText;
}
