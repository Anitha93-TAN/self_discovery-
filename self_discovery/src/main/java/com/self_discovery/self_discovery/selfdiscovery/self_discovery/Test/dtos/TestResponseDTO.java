package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TestResponseDTO {
    private Long testId;
    private String title;
    private String description;
    private LocalDate linkExpiryDate;
    private List<InterpretationResponseDTO> interpretations;
    private List<RecommendationResponseDTO> recommendations;
    private List<SectionResponseDTO> sections;
}
