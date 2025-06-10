package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AnswerOptionUpdateResponseDTO {
private Long answerOptionId;
private String answerText;
private OptionValue optionValue;
private int score;
}