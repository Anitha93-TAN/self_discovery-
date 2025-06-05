package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import lombok.Data;

@Data
public class SectionInterpretationRequestDTO {
   // private Long sectionInterpretationId;
    private int sectionId;
    private int minScore;
    private int maxScore;
    private String description;
}
