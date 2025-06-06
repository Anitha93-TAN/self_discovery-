package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TestRequestDTO {
    private String testTitle;
    private String description;
    private LocalDate linkExpiryDate;
    private List<SectionRequestDTO> sections;
    private List<InterpretationRequestDTO> interpretations;
    private List<RecommendationRequestDTO> recommendations;

}
