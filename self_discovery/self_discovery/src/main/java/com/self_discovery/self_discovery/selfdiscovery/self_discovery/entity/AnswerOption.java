package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import com.self_discovery.self_discovery.selfdiscovery.registration.domain.base.BaseEntity;
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
@Table(name = "answer_option")
public class AnswerOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_option_id")
    private Long answerOptionId;

    @Column(name = "answer_text", nullable = true)
    private String answerText;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_value", unique = true, nullable = true)
    private OptionValue optionValue;

    @Column(name = "score")
    private int score;

    @ManyToMany(mappedBy = "answerOptions") // ✅ REMOVED cascade
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Question> questions;
}
