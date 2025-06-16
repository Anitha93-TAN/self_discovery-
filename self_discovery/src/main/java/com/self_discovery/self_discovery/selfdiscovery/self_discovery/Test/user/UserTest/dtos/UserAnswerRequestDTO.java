package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import lombok.Data;

import java.util.List;

@Data
public class UserAnswerRequestDTO {
    private Long questionId;
    private OptionValue optionValue;
    private String answerText;
    private List<UserAnswerRequestDTO> userAnswers;

}
