package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDTO {
    private Long questionId;
    private String questionText;
    private AnswerType answerType;
    private int questionOrder;
    private List<AnswerOptionResponseDTO> answerOptions;
}
