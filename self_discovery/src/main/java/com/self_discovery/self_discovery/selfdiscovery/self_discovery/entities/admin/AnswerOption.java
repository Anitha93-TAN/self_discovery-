package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin;


import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer_option")
public class AnswerOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_option_id")
    private Long answerOptionId;

    @Column(name = "answer_text")
    private String answerText;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_value")
    private OptionValue optionValue;

    @Column(name = "score")
    private int score;

    @ManyToMany(mappedBy = "answerOptions")
    private Set<Question> questions;

   /* @OneToMany(mappedBy = "answerOption",cascade = CascadeType.ALL)
    private List<QuestionResponse> questionResponses; */

}
