package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import lombok.Data;
import java.util.List;

@Data
public class QuestionResponseDTO {
    private Integer questionId;
    private String questionText;
    private String answerType;
    private int questionOrder;
    private List<AnswerOptionResponseDTO> answerOptions;
}
