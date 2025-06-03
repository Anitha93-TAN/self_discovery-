package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;
import java.util.List;

@Data
public class QuestionRequestDTO {
    private Integer questionId;
    private String questionText;
    private String answerType;  // enum value as String
    private int questionOrder;
    private List<AnswerOptionRequestDTO> answerOptions;
}
