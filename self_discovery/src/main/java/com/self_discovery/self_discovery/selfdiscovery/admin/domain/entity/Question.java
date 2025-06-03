package com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.base.BaseEntity;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.AnswerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }) // Avoid ALL to prevent accidental deletes
    @JoinTable(
            name = "question_answer_option",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_option_id")
    )
    private Set<AnswerOption> answerOptions = new HashSet<>();



}
