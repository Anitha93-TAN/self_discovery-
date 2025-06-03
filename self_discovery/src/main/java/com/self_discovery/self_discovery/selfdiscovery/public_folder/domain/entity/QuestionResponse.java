package com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_response")
public class QuestionResponse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_response_id")
    private int userResponseId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_option_id")
    private AnswerOption answerOption;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "rating_value")
    private Integer ratingValue;

}
