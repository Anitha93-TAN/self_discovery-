package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos;

import lombok.Data;

@Data
public class SectionUpdateRequestDTO {
    private long sectionId;
    private String sectionTitle;
    private int sectionOrder;
    private boolean randomizeQuestions;
}
