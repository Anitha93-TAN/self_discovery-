package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SectionRequestDTO {
    private String sectionTitle;
    private int sectionOrder;
    private boolean randomizeQuestions;
    private List<QuestionRequestDTO> questions;
    private List<SectionInterpretationRequestDTO> sectionInterpretation;
}
