package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.service;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.repository.TestRepository;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import com.self_discovery.self_discovery.selfdiscovery.utils.ResponseHandler;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private ResponseHandler<TestResponseDTO> responseHandler;

    @Autowired
    private ResponseHandler<List<TestResponseDTO>> listResponseHandler;

    @Override
    @Transactional
    public ApiResponse<TestResponseDTO> createTest(TestRequestDTO testRequestDTO) {
        Test testEntity = mapToTestEntity(testRequestDTO);
        Test savedTest = testRepository.save(testEntity);
        TestResponseDTO responseDTO = mapToTestResponseDTO(savedTest);
        return responseHandler.success(responseDTO, "Test created successfully", HttpStatusCodes.OK);
    }

    @Override
    @Transactional
    public ApiResponse<List<TestResponseDTO>> getAllTests() {
        List<Test> tests = testRepository.findAll();
        List<TestResponseDTO> testDTOs = new ArrayList<>();
        for (Test test : tests) {
            testDTOs.add(mapToTestResponseDTO(test));
        }
        return listResponseHandler.success(testDTOs, "All tests fetched successfully", HttpStatusCodes.OK);
    }

    private Test mapToTestEntity(TestRequestDTO dto) {
        Test test = new Test();
        test.setTitle(dto.getTitle());
        test.setDescription(dto.getDescription());
        test.setLinkExpiryDate(dto.getLinkExpiryDate());

        List<Section> sections = new ArrayList<>();
        if (dto.getSections() != null) {
            for (SectionRequestDTO sectionDTO : dto.getSections()) {
                Section section = new Section();
                section.setTitle(sectionDTO.getTitle());
                section.setSectionOrder(sectionDTO.getSectionOrder());
                section.setRandomizeQuestions(sectionDTO.isRandomizeQuestions());
                section.setTest(test);

                List<Question> questions = new ArrayList<>();
                if (sectionDTO.getQuestions() != null) {
                    for (QuestionRequestDTO questionDTO : sectionDTO.getQuestions()) {
                        Question question = new Question();
                        question.setQuestionText(questionDTO.getQuestionText());
                        question.setAnswerType(questionDTO.getAnswerType());
                        question.setQuestionOrder(questionDTO.getQuestionOrder());
                        question.setSection(section);

                        List<AnswerOptionRequestDTO> optionDTOs = questionDTO.getAnswerOptions();
                        List<AnswerOption> options = new ArrayList<>();

                        // Prepare map of existing AnswerOptions for reuse (by OptionValue)
                        Map<OptionValue, AnswerOption> existingOptionMap = new HashMap<>();
                        if ((questionDTO.getAnswerType() == AnswerType.SINGLE_CHOICE ||
                                questionDTO.getAnswerType() == AnswerType.RATING) &&
                                optionDTOs != null && !optionDTOs.isEmpty()) {

                            List<OptionValue> valuesToCheck = new ArrayList<>();
                            for (AnswerOptionRequestDTO dtoOpt : optionDTOs) {
                                if (dtoOpt.getOptionValue() != null) {
                                    valuesToCheck.add(dtoOpt.getOptionValue());
                                }
                            }

                            if (!valuesToCheck.isEmpty()) {
                                List<AnswerOption> existingOptions =
                                        answerOptionRepository.findByOptionValueIn(valuesToCheck);
                                for (AnswerOption opt : existingOptions) {
                                    existingOptionMap.put(opt.getOptionValue(), opt);
                                }
                            }
                        }

                        // Process each option
                        if (optionDTOs != null) {
                            for (AnswerOptionRequestDTO optionDTO : optionDTOs) {
                                AnswerOption option;

                                if ((questionDTO.getAnswerType() == AnswerType.SINGLE_CHOICE ||
                                        questionDTO.getAnswerType() == AnswerType.RATING) &&
                                        optionDTO.getOptionValue() != null &&
                                        existingOptionMap.containsKey(optionDTO.getOptionValue())) {

                                    // Reuse existing option
                                    option = existingOptionMap.get(optionDTO.getOptionValue());
                                } else {
                                    // Create new option
                                    option = new AnswerOption();
                                    option.setAnswerText(optionDTO.getAnswerText());
                                    option.setScore(optionDTO.getScore());
                                    option.setOptionValue(optionDTO.getOptionValue());
                                }

                                // *** IMPORTANT: keep bidirectional association in sync ***
                                if (option.getQuestions() == null) {
                                    option.setQuestions(new HashSet<>());
                                }
                                option.getQuestions().add(question);

                                options.add(option);
                            }
                        }

                        question.setAnswerOptions(new HashSet<>(options));
                        questions.add(question);
                    }
                }

                section.setQuestions(questions);

                // Section interpretations
                List<SectionInterpretation> sectionInterpretations = new ArrayList<>();
                if (sectionDTO.getSectionInterpretation() != null) {
                    for (SectionInterpretationRequestDTO interpDTO : sectionDTO.getSectionInterpretation()) {
                        SectionInterpretation interpretation = new SectionInterpretation();
                        interpretation.setMinScore(interpDTO.getMinScore());
                        interpretation.setMaxScore(interpDTO.getMaxScore());
                        interpretation.setDescription(interpDTO.getDescription());
                        interpretation.setSection(section);
                        sectionInterpretations.add(interpretation);
                    }
                }
                section.setSectionInterpretations(sectionInterpretations);
                sections.add(section);
            }
        }

        test.setSections(sections);

        // Test interpretations
        List<Interpretation> interpretations = new ArrayList<>();
        if (dto.getInterpretations() != null) {
            for (InterpretationRequestDTO interpDTO : dto.getInterpretations()) {
                Interpretation interpretation = new Interpretation();
                interpretation.setMinScore(interpDTO.getMinScore());
                interpretation.setMaxScore(interpDTO.getMaxScore());
                interpretation.setDescription(interpDTO.getDescription());
                interpretation.setTest(test);
                interpretations.add(interpretation);
            }
        }
        test.setInterpretations(interpretations);

        // Recommendations
        List<Recommendation> recommendations = new ArrayList<>();
        if (dto.getRecommendations() != null) {
            for (RecommendationRequestDTO recDTO : dto.getRecommendations()) {
                Recommendation recommendation = new Recommendation();
                recommendation.setMinScore(recDTO.getMinScore());
                recommendation.setMaxScore(recDTO.getMaxScore());
                recommendation.setRecommendationText(recDTO.getRecommendationText());
                recommendation.setTest(test);
                recommendations.add(recommendation);
            }
        }
        test.setRecommendations(recommendations);

        return test;
    }

    private TestResponseDTO mapToTestResponseDTO(Test entity) {
        TestResponseDTO dto = new TestResponseDTO();
        dto.setTestId(entity.getTestId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setLinkExpiryDate(entity.getLinkExpiryDate());

        List<SectionResponseDTO> sectionDTOs = new ArrayList<>();
        if (entity.getSections() != null) {
            for (Section section : entity.getSections()) {
                SectionResponseDTO sectionDTO = new SectionResponseDTO();
                sectionDTO.setSectionId(section.getSectionId());
                sectionDTO.setTitle(section.getTitle());
                sectionDTO.setSectionOrder(section.getSectionOrder());
                sectionDTO.setRandomizeQuestions(section.isRandomizeQuestions());

                List<QuestionResponseDTO> questionDTOs = new ArrayList<>();
                if (section.getQuestions() != null) {
                    for (Question question : section.getQuestions()) {
                        QuestionResponseDTO questionDTO = new QuestionResponseDTO();
                        questionDTO.setQuestionId(question.getQuestionId());
                        questionDTO.setQuestionText(question.getQuestionText());
                        questionDTO.setAnswerType(question.getAnswerType());
                        questionDTO.setQuestionOrder(question.getQuestionOrder());

                        List<AnswerOptionResponseDTO> optionDTOs = new ArrayList<>();
                        if (question.getAnswerOptions() != null) {
                            for (AnswerOption option : question.getAnswerOptions()) {
                                AnswerOptionResponseDTO optionDTO = new AnswerOptionResponseDTO();
                                optionDTO.setAnswerOptionId(option.getAnswerOptionId());
                                optionDTO.setAnswerText(option.getAnswerText());
                                optionDTO.setOptionValue(option.getOptionValue());
                                optionDTO.setScore(option.getScore());
                                optionDTOs.add(optionDTO);
                            }
                        }
                        questionDTO.setAnswerOptions(optionDTOs);
                        questionDTOs.add(questionDTO);
                    }
                }
                sectionDTO.setQuestions(questionDTOs);

                List<SectionInterpretationResponseDTO> sectionInterpDTOs = new ArrayList<>();
                if (section.getSectionInterpretations() != null) {
                    for (SectionInterpretation si : section.getSectionInterpretations()) {
                        SectionInterpretationResponseDTO siDTO = new SectionInterpretationResponseDTO();
                        siDTO.setSectionInterpretationId(si.getSectionInterpretationId());
                        siDTO.setSectionId(Math.toIntExact(si.getSection().getSectionId()));
                        siDTO.setMinScore(si.getMinScore());
                        siDTO.setMaxScore(si.getMaxScore());
                        siDTO.setDescription(si.getDescription());
                        sectionInterpDTOs.add(siDTO);
                    }
                }
                sectionDTO.setSectionInterpretationResponseDTOS(sectionInterpDTOs);
                sectionDTOs.add(sectionDTO);
            }
        }

        dto.setSections(sectionDTOs);

        List<InterpretationResponseDTO> interpretationDTOs = new ArrayList<>();
        if (entity.getInterpretations() != null) {
            for (Interpretation interpretation : entity.getInterpretations()) {
                InterpretationResponseDTO interpDTO = new InterpretationResponseDTO();
                interpDTO.setInterpretationId(interpretation.getInterpretationId());
                interpDTO.setMinScore(interpretation.getMinScore());
                interpDTO.setMaxScore(interpretation.getMaxScore());
                interpDTO.setDescription(interpretation.getDescription());
                interpretationDTOs.add(interpDTO);
            }
        }
        dto.setInterpretations(interpretationDTOs);

        List<RecommendationResponseDTO> recommendationDTOs = new ArrayList<>();
        if (entity.getRecommendations() != null) {
            for (Recommendation recommendation : entity.getRecommendations()) {
                RecommendationResponseDTO recDTO = new RecommendationResponseDTO();
                recDTO.setRecommendationId(recommendation.getRecommendationId());
                recDTO.setMinScore(recommendation.getMinScore());
                recDTO.setMaxScore(recommendation.getMaxScore());
                recDTO.setRecommendationText(recommendation.getRecommendationText());
                recommendationDTOs.add(recDTO);
            }
        }

        dto.setRecommendations(recommendationDTOs);
        return dto;
    }
}
