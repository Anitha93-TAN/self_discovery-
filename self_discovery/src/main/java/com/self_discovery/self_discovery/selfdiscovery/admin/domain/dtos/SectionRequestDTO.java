package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;
import java.util.List;

@Data
public class SectionRequestDTO {
    private Long sectionId;
    private String title;
    private int sectionOrder;
    private boolean randomizeQuestions;
    private List<QuestionRequestDTO> questions;
    private List<SectionInterpretationRequestDTO> sectionInterpretation;
}
