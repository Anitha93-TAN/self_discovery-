package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;

@Data
public class AnswerOptionResponseDTO {
    private Integer answerOptionId;
    private String answerText;
    private String optionValue;
    private int score;
}
