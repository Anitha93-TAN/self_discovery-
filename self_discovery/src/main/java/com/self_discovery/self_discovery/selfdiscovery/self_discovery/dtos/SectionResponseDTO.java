package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import lombok.Data;
import java.util.List;

@Data
public class SectionResponseDTO {
    private Long sectionId;
    private String sectionTitle;
    private int sectionOrder;
    private boolean randomizeQuestions;
    private List<QuestionResponseDTO> questions;
    private List<SectionInterpretationResponseDTO> sectionInterpretationResponse;



}
