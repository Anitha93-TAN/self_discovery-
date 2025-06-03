package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpretationResponseDTO {
    private int interpretationId;
    private int testId;
    private String testName;
    private int minScore;
    private int maxScore;
    private String description;
}
