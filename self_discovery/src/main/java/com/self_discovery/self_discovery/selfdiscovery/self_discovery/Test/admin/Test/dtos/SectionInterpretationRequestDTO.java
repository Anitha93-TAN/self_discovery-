package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos;

import lombok.Data;

@Data
public class SectionInterpretationRequestDTO {
    private String title;
    private int minScore;
    private int maxScore;
    private String description;
}
