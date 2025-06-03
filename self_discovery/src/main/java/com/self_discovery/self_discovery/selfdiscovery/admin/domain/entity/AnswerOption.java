package com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.base.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer_option",
        uniqueConstraints = @UniqueConstraint(columnNames = {"option_value"}))
public class AnswerOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_option_id")
    private Long answerOptionId;

    // For custom MCQ text option
    @Column(name = "answer_text", nullable = true)  // nullable true, since enum or text exclusive
    private String answerText;

    // For enum predefined options (unique)
    @Enumerated(EnumType.STRING)
    @Column(name = "option_value", unique = true, nullable = true)
    private OptionValue optionValue;

    @Column(name = "score")
    private int score;

    @ManyToMany(mappedBy = "answerOptions", cascade = CascadeType.ALL)
    private Set<Question> questions;


}
