package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import lombok.Data;

@Data
public class AnswerOptionResponseDTO {
    private Long answerOptionId;
    private String answerText;
    private OptionValue optionValue;
    private int score;
}
