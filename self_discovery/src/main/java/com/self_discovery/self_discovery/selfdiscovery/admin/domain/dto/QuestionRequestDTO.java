package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.enums.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {
    private int sectionId; // to link with Section entity by id
    private String questionText;
    private AnswerType answerType;
    private int questionOrder;
    // IDs of AnswerOption entities
}
