package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos;

import lombok.Data;

@Data
public class SectionInterpretationUpdateResponseDTO {
    private Long sectionInterpretationId;
    private String title;
    private int minScore;
    private int maxScore;
    private String description;
}
