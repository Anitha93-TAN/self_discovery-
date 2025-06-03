package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;

@Data
public class AnswerOptionRequestDTO {
    private Integer answerOptionId;
    private String answerText;     // custom MCQ text option
    private String optionValue;    // enum string for predefined option
    private int score;
}
