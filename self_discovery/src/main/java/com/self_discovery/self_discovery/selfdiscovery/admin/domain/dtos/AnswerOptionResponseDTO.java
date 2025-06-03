package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import lombok.Data;

@Data
public class AnswerOptionResponseDTO {
    private Long answerOptionId;
    private String answerText;
    private OptionValue optionValue;
    private int score;
}
