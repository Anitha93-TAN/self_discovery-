package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import lombok.Data;

import java.util.Set;

@Data
public class QuestionRequestDTO {
    private String questionText;
    private AnswerType answerType;  // enum value as String
    private int questionOrder;
    private Set<AnswerOptionRequestDTO> answerOptions;
}
