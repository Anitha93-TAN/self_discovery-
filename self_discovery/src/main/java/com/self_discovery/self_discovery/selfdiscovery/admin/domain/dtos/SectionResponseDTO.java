package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;
import java.util.List;

@Data
public class SectionResponseDTO {
    private Integer sectionId;
    private String title;
    private int sectionOrder;
    private boolean randomizeQuestions;
    private List<QuestionResponseDTO> questions;
    private List<SectionInterpretationResponseDTO> sectionInterpretationResponseDTOS;

}
