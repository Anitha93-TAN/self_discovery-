package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.enums.AnswerType;
import lombok.Data;

import java.util.Set;
@Data
public class QuestionResponseDTO {
    private int questionId;
    private int sectionId; // to link with Section entity by id
    private String questionText;
    private AnswerType answerType;
    private int questionOrder;
    private Set<Integer> answerOptionIds; // IDs of AnswerOption entities
}
