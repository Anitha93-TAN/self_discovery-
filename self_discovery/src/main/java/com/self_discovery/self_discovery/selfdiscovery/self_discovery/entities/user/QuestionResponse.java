package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.Question;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "question_response")
public class QuestionResponse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_response_id")
    private Long userResponseId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_option_id")
    private AnswerOption answerOption;


    @Column(name = "rating_value")
    private int ratingValue;

}
