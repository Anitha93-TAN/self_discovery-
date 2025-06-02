package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import lombok.Data;

@Data
public class SectionRequestDto {
    private int testId;
    private String title;
    private int sectionOrder;
    private boolean randomizeQuestions;
}
