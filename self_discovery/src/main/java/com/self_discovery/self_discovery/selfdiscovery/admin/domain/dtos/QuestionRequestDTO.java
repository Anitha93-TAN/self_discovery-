package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.AnswerType;
import lombok.Data;
import java.util.List;

@Data
public class QuestionRequestDTO {
    private Long questionId;
    private String questionText;
    private AnswerType answerType;  // enum value as String
    private int questionOrder;
    private List<AnswerOptionRequestDTO> answerOptions;
}
