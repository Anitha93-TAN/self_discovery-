package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpretationRequestDTO {
    private int testId;
    private int minScore;
    private int maxScore;
    private String description;
}