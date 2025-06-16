package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.implementations;

import com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler.CustomException;
import com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler.NotFoundException;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces.IQuestionService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.Question;
import com.self_discovery.self_discovery.selfdiscovery.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.repository.QuestionRepository;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    private static final Map<OptionValue, String> RATING_TEXT_MAP = Map.of(
            OptionValue.ONE, "1",
            OptionValue.TWO, "2",
            OptionValue.THREE, "3",
            OptionValue.FOUR, "4",
            OptionValue.FIVE, "5",
            OptionValue.SIX, "6",
            OptionValue.SEVEN, "7",
            OptionValue.EIGHT, "8",
            OptionValue.NINE, "9",
            OptionValue.TEN, "10"
    );

    // ---------------- GET ALL ----------------
    @Override
    @Transactional
    public ApiResponse<List<QuestionUpdateResponseDTO>> getAll() {
        List<QuestionUpdateResponseDTO> list = questionRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new ApiResponse<>(HttpStatusCodes.OK, "Questions fetched successfully", list);
    }

    // ---------------- GET BY ID ----------------
    @Override
    @Transactional
    public ApiResponse<QuestionUpdateResponseDTO> getById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Question not found with ID: " + id));
        return new ApiResponse<>(HttpStatusCodes.OK, "Question fetched successfully", toDto(question));
    }

    // ---------------- UPDATE ----------------
    @Override
    @Transactional
    public ApiResponse<QuestionUpdateResponseDTO> update(Long id, QuestionUpdateRequestDTO dto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with ID: " + id));

        question.setQuestionText(dto.getQuestionText());
        question.setAnswerType(dto.getAnswerType());
        question.setQuestionOrder(dto.getQuestionOrder());

        List<AnswerOption> updatedOptions = dto.getAnswerOptions().stream().map(opt -> {
            AnswerOption option = answerOptionRepository.findById(opt.getAnswerOptionId())
                    .orElseThrow(() -> new NotFoundException("AnswerOption not found with ID: " + opt.getAnswerOptionId()));

            String answerText = opt.getAnswerText().trim();
            OptionValue optionValue = opt.getOptionValue();

            // SINGLE_CHOICE validation
            if (dto.getAnswerType() == AnswerType.SINGLE_CHOICE) {
                if ((optionValue == OptionValue.YES || optionValue == OptionValue.NO) &&
                        !(answerText.equalsIgnoreCase("Yes") || answerText.equalsIgnoreCase("No"))) {
                    throw new CustomException("SINGLE_CHOICE must have 'Yes' or 'No' as answerText for YES/NO options.");
                }
            }

            // RATING validation
            if (dto.getAnswerType() == AnswerType.RATING) {
                if (!RATING_TEXT_MAP.containsKey(optionValue)) {
                    throw new CustomException("RATING must have OptionValue between ONE to TEN only.");
                }
                String expectedText = RATING_TEXT_MAP.get(optionValue);
                if (!answerText.equals(expectedText)) {
                    throw new CustomException("RATING OptionValue " + optionValue + " must have answerText: " + expectedText);
                }
            }

            option.setAnswerText(answerText);
            option.setOptionValue(optionValue);
            option.setScore(opt.getScore());
            return option;
        }).collect(Collectors.toList());

        question.setAnswerOptions(updatedOptions);
        Question updated = questionRepository.save(question);
        return new ApiResponse<>(HttpStatusCodes.OK, "Question updated successfully", toDto(updated));
    }

    // ---------------- DELETE ALL ----------------
    @Override
    @Transactional
    public ApiResponse<Void> deleteAll() {
        questionRepository.deleteAll();
        return new ApiResponse<>(HttpStatusCodes.OK, "All questions deleted successfully", null);
    }

    // ---------------- DELETE BY ID ----------------
    @Override
    @Transactional
    public ApiResponse<Void> deleteById(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new NotFoundException("Question not found with ID: " + id);
        }
        questionRepository.deleteById(id);
        return new ApiResponse<>(HttpStatusCodes.OK, "Question deleted successfully", null);
    }

    // ---------------- DTO MAPPING ----------------
    private QuestionUpdateResponseDTO toDto(Question q) {
        QuestionUpdateResponseDTO dto = new QuestionUpdateResponseDTO();
        dto.setQuestionId(q.getQuestionId());
        dto.setQuestionText(q.getQuestionText());
        dto.setAnswerType(q.getAnswerType());
        dto.setQuestionOrder(q.getQuestionOrder());
        dto.setCustomSingleChoice(
                q.getAnswerType() == AnswerType.SINGLE_CHOICE &&
                        q.getAnswerOptions().stream().anyMatch(opt ->
                                opt.getOptionValue() != null &&
                                        !(opt.getOptionValue() == OptionValue.YES || opt.getOptionValue() == OptionValue.NO)
                        )
        );
        dto.setAnswerOptions(
                q.getAnswerOptions().stream().map(opt -> {
                    AnswerOptionUpdateRequestDTO resp = new AnswerOptionUpdateRequestDTO();
                    resp.setAnswerOptionId(opt.getAnswerOptionId());
                    resp.setAnswerText(opt.getAnswerText());
                    resp.setOptionValue(opt.getOptionValue());
                    resp.setScore(opt.getScore());
                    return resp;
                }).collect(Collectors.toList())
        );
        return dto;
    }
}
