package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.implementations;

import com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler.NotFoundException;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces.IQuestionService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.repository.QuestionRepository;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.AnswerOptionResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Override
    @Transactional
    public ApiResponse<List<QuestionUpdateResponseDTO>> getAll() {
        List<QuestionUpdateResponseDTO> list = questionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
        return new ApiResponse<>(HttpStatusCodes.OK, "Questions fetched successfully", list);
    }

    @Override
    @Transactional
    public ApiResponse<QuestionUpdateResponseDTO> getById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Question not found with ID: " + id));
        return new ApiResponse<>(HttpStatusCodes.OK, "Question fetched successfully", toDto(question));
    }

    @Override
    @Transactional
    public ApiResponse<QuestionUpdateResponseDTO> update(Long id, QuestionUpdateRequestDTO dto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with ID: " + id));

        question.setQuestionText(dto.getQuestionText());
        question.setAnswerType(dto.getAnswerType());
        question.setQuestionOrder(dto.getQuestionOrder());

        List<AnswerOption> answerOptions = dto.getAnswerOptions().stream()
                .map(opt -> answerOptionRepository.findById(opt.getAnswerOptionId())
                        .orElseThrow(() -> new NotFoundException("AnswerOption not found with ID: " + opt.getAnswerOptionId())))
                .collect(Collectors.toList());

        question.setAnswerOptions(answerOptions);
        Question updated = questionRepository.save(question);
        return new ApiResponse<>(HttpStatusCodes.OK, "Question updated successfully", toDto(updated));
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteAll() {
        questionRepository.deleteAll();
        return new ApiResponse<>(HttpStatusCodes.OK, "All questions deleted successfully", null);
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteById(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new NotFoundException("Question not found with ID: " + id);
        }
        questionRepository.deleteById(id);
        return new ApiResponse<>(HttpStatusCodes.OK, "Question deleted successfully", null);
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
