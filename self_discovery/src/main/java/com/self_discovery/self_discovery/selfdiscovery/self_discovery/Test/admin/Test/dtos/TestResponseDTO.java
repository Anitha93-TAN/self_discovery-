package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TestResponseDTO {
    private Long testId;
    private String testTitle;
    private String description;
    private LocalDate linkExpiryDate;
    private List<SectionResponseDTO> sections;
    private List<InterpretationResponseDTO> interpretations;
    private List<RecommendationResponseDTO> recommendations;

}
