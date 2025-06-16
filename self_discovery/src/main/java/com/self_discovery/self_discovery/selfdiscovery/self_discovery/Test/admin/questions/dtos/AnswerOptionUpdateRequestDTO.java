package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import lombok.Data;

@Data
public class AnswerOptionUpdateRequestDTO {
        private Long answerOptionId;
        private String answerText;
        private OptionValue optionValue;
        private int score;
}