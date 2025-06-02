package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import lombok.Data;

import java.util.List;

@Data
public class AnswerOptionRequestDTO {
    private String answerText;
    private OptionValue optionValue;
    private int score;
    private List<Integer> questionIds; // IDs of associated questions
}
