package com.self_discovery.self_discovery.selfdiscovery.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.domain.base.BaseEntity;
import com.self_discovery.self_discovery.selfdiscovery.domain.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.domain.enums.AnswerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type", nullable = false)
    private AnswerType answerType;

    @Column(name = "order", nullable = false)
    private int order;


    @ManyToMany
    @JoinTable(
            name = "question_answer_option",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_option_id")
    )
    private Set<AnswerOption> answerOptions;


}
