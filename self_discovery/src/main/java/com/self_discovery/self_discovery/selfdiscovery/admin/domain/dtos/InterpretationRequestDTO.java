package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;

@Data
public class InterpretationRequestDTO {
    private Integer interpretationId;
    private int minScore;
    private int maxScore;
    private String description;
}
