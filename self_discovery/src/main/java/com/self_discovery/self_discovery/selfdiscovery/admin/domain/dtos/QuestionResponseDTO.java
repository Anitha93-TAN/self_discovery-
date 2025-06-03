package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.AnswerType;
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
