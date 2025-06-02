package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionResponseDTO;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public AnswerOptionResponseDTO createAnswerOption(AnswerOptionRequestDTO requestDTO) {
        AnswerOption answerOption = new AnswerOption();
        answerOption.setAnswerText(requestDTO.getAnswerText());
        answerOption.setOptionValue(requestDTO.getOptionValue());
        answerOption.setScore(requestDTO.getScore());

        if (requestDTO.getQuestionIds() != null) {
            Set<Question> questions = requestDTO.getQuestionIds().stream()
                    .map(id -> questionRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            answerOption.setQuestions(questions);
        }

        AnswerOption saved = answerOptionRepository.save(answerOption);

        // Convert to ResponseDTO
        AnswerOptionResponseDTO responseDTO = new AnswerOptionResponseDTO();
        responseDTO.setAnswerOptionId(saved.getAnswerOptionId());
        responseDTO.setAnswerText(saved.getAnswerText());
        responseDTO.setOptionValue(saved.getOptionValue());
        responseDTO.setScore(saved.getScore());

        if (saved.getQuestions() != null) {
            List<AnswerOptionResponseDTO.QuestionInfo> questionInfos = saved.getQuestions().stream().map(q -> {
                AnswerOptionResponseDTO.QuestionInfo info = new AnswerOptionResponseDTO.QuestionInfo();
                //info.setQuestionId(q.getId());
                info.setQuestionId(q.getQuestionId());
                info.setQuestionText(q.getQuestionText());
                return info;
            }).collect(Collectors.toList());
            responseDTO.setQuestions(questionInfos);
        }

        return responseDTO;
    }
}
