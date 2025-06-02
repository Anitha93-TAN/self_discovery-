package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import lombok.Data;

import java.util.List;

@Data
public class AnswerOptionResponseDTO {
    private int answerOptionId;
    private String answerText;
    private OptionValue optionValue;
    private int score;
    private List<QuestionInfo> questions;

    @Data
    public static class QuestionInfo {
        private int questionId;
        private String questionText;
    }
}
