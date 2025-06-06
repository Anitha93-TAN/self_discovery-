package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import lombok.Data;

@Data
public class AnswerOptionRequestDTO {
    private String answerText;     // custom MCQ text option
    private OptionValue optionValue;    // enum string for predefined option
    private int score;
}
