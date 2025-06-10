package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.AnswerOptionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class QuestionUpdateRequestDTO {
        private String questionText;
        private AnswerType answerType;
        private int questionOrder;
        private List<AnswerOptionUpdateRequestDTO> answerOptions;

}
