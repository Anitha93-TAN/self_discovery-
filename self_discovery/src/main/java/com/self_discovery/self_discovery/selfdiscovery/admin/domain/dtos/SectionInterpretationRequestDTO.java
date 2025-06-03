package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;

@Data
public class SectionInterpretationRequestDTO {
    private int sectionInterpretationId;
    private int sectionId;
    private int minScore;
    private int maxScore;
    private String description;
}
