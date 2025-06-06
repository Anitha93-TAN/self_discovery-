package com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import lombok.Data;
import java.util.List;

@Data
public class QuestionRequestDTO {
    private String questionText;
    private AnswerType answerType;  // enum value as String
    private int questionOrder;
    private List<AnswerOptionRequestDTO> answerOptions;
}
