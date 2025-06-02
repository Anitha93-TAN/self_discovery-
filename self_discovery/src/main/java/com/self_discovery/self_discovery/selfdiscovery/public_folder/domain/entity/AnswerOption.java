package com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.base.BaseEntity;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.enums.OptionValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private int answerOptionId;

    @Column(name = "answer_text", nullable = false)
    private String answerText;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_value")
    private OptionValue optionValue;

    @Column(name = "score")
    private int score;

    @ManyToMany(mappedBy = "answerOptions")
    private Set<Question> questions;

}
