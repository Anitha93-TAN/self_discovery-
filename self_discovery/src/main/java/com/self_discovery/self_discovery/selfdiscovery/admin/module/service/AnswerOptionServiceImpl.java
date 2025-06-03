package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public AnswerOption save(AnswerOptionRequestDTO dto) {
        AnswerOption answerOption = new AnswerOption();
        answerOption.setAnswerText(dto.getAnswerText());
        answerOption.setOptionValue(dto.getOptionValue());
        answerOption.setScore(dto.getScore());

        Optional<Question> questionOpt = questionRepository.findById(dto.getQuestionId());
        if (questionOpt.isEmpty()) {
            throw new RuntimeException("Question with id " + dto.getQuestionId() + " not found");
        }

        Question question = questionOpt.get();

        // Link answerOption with question
        Set<AnswerOption> answerOptions = question.getAnswerOptions();
        if (answerOptions == null) {
            answerOptions = new HashSet<>();
        }
        answerOptions.add(answerOption);
        question.setAnswerOptions(answerOptions);

        // Save answerOption first to get the ID generated
        answerOption = answerOptionRepository.save(answerOption);

        // Save updated question with new answerOption
        questionRepository.save(question);

        return answerOption;
    }
}
