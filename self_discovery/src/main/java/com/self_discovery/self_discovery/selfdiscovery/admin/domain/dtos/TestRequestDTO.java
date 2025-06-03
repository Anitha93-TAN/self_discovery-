package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TestRequestDTO {
    private Long testId;  // for update, null for create
    private String title;
    private String description;
    private LocalDate linkExpiryDate;
    private List<InterpretationRequestDTO> interpretations;
    private List<RecommendationRequestDTO> recommendations;
    private List<SectionRequestDTO> sections;
}
