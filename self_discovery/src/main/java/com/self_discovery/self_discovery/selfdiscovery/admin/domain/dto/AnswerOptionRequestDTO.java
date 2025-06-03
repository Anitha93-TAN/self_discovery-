package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import lombok.Data;

@Data
public class AnswerOptionRequestDTO {
    private int questionId; // optional, for update
    private String answerText;
    private OptionValue optionValue;
    private int score;
}

