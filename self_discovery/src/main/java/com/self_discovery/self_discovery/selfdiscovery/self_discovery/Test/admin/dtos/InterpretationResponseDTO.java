package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.dtos;

import lombok.Data;

@Data
public class InterpretationResponseDTO {

    private Long interpretationId;
    private String title;
    private int minScore;
    private int maxScore;
    private String description;
}
