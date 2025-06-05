package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import lombok.Data;

@Data
public class InterpretationResponseDTO {
    private Long interpretationId;
    private int minScore;
    private int maxScore;
    private String description;
}
