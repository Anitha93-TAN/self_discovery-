package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.self_discovery.self_discovery.selfdiscovery.registration.domain.base.BaseEntity;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
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
    @JoinColumn(name = "section_id", nullable = false)
    @JsonBackReference
    private Section section;

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type", nullable = false)
    private AnswerType answerType;

    @Column(name = "question_order", nullable = false)
    private int questionOrder;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "question_answer_option",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_option_id")
    )
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<AnswerOption> answerOptions;



}
