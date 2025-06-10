package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.implementations;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces.IQuestionService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.repository.QuestionRepository;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.AnswerOptionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;

    @Override
    public List<QuestionUpdateResponseDTO> getAll() {
        return questionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public QuestionUpdateResponseDTO getById(Long id) {
        return questionRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public QuestionUpdateResponseDTO update(Long id, QuestionUpdateRequestDTO dto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuestionText(dto.getQuestionText());
        question.setAnswerType(dto.getAnswerType());
        question.setQuestionOrder(dto.getQuestionOrder());

        List<AnswerOption> answerOptions = dto.getAnswerOptions().stream()
                .map(opt -> answerOptionRepository.findById(opt.getAnswerOptionId())
                        .orElseThrow(() -> new RuntimeException("AnswerOption not found")))
                .collect(Collectors.toList());

        question.setAnswerOptions(answerOptions);
        return toDto(questionRepository.save(question));
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    private QuestionUpdateResponseDTO toDto(Question q) {
        QuestionUpdateResponseDTO dto = new QuestionUpdateResponseDTO();
        dto.setQuestionId(q.getQuestionId());
        dto.setQuestionText(q.getQuestionText());
        dto.setAnswerType(q.getAnswerType());
        dto.setQuestionOrder(q.getQuestionOrder());
        dto.setAnswerOptions(q.getAnswerOptions().stream().map(opt -> {
            AnswerOptionResponseDTO resp = new AnswerOptionResponseDTO();
            resp.setAnswerOptionId(opt.getAnswerOptionId());
            resp.setAnswerText(opt.getAnswerText());
            resp.setOptionValue(opt.getOptionValue());
            resp.setScore(opt.getScore());
            return resp;
        }).collect(Collectors.toList()));
        return dto;
    }
}