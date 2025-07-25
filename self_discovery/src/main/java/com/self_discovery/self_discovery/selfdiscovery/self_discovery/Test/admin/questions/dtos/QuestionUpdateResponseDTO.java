package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.AnswerOptionResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import lombok.Data;
import java.util.List;


@Data
public class QuestionUpdateResponseDTO {
    private Long questionId;
    private String questionText;
    private AnswerType answerType;
    private int questionOrder;
    private boolean customSingleChoice;
    private List<AnswerOptionUpdateRequestDTO> answerOptions;
}
