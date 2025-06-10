package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Section;
import lombok.Data;


@Data
public class SectionInterpretationUpdateRequestDTO {

    private Long sectionInterpretationId;
    private String title;
    private int minScore;
    private int maxScore;
    private String description;
}
