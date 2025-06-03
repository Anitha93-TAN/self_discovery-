package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import lombok.Data;

@Data
public class AnswerOptionRequestDTO {
    private Long answerOptionId;
    private String answerText;     // custom MCQ text option
    private OptionValue optionValue;    // enum string for predefined option
    private int score;
}
