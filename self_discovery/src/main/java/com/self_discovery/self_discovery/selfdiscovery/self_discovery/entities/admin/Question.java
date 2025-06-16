package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin;


import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.QuestionResponse;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "question_text")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type")
    private AnswerType answerType;

    @Column(name = "question_order")
    private int questionOrder;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "question_answer_option",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_option_id")
    )
    private List<AnswerOption> answerOptions;


    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<QuestionResponse> questionResponses;

}
