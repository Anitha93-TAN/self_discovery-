package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.service.service.TestService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.AnswerType;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import com.self_discovery.self_discovery.selfdiscovery.repository.AnswerOptionRepository;
import com.self_discovery.self_discovery.selfdiscovery.repository.TestRepository;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import com.self_discovery.self_discovery.selfdiscovery.utils.ResponseHandler;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<TestResponseDTO> testDTOs = tests.stream()
                .map(this::mapToTestResponseDTO)
                .collect(Collectors.toList());
        return listResponseHandler.success(testDTOs, "All tests fetched successfully", HttpStatusCodes.OK);
    }

    private Test mapToTestEntity(TestRequestDTO dto) {
        Test test = new Test();
        test.setTitle(dto.getTitle());
        test.setDescription(dto.getDescription());
        test.setLinkExpiryDate(dto.getLinkExpiryDate());

        if (dto.getSections() != null) {
            List<Section> sections = dto.getSections().stream()
                    .map(sectionDTO -> {
                        Section section = new Section();
                        section.setTitle(sectionDTO.getTitle());
                        section.setSectionOrder(sectionDTO.getSectionOrder());
                        section.setRandomizeQuestions(sectionDTO.isRandomizeQuestions());
                        section.setTest(test);

                        if (sectionDTO.getQuestions() != null) {
                            List<Question> questions = sectionDTO.getQuestions().stream()
                                    .map(questionDTO -> {
                                        Question question = new Question();
                                        question.setQuestionText(questionDTO.getQuestionText());
                                        question.setAnswerType(questionDTO.getAnswerType());
                                        question.setQuestionOrder(questionDTO.getQuestionOrder());
                                        question.setSection(section);

                                        List<AnswerOptionRequestDTO> optionDTOs = questionDTO.getAnswerOptions();
                                        List<AnswerOption> options = new ArrayList<>();

                                        if (optionDTOs != null && !optionDTOs.isEmpty()) {
                                            Map<String, AnswerOption> optionCache = new HashMap<>();

                                            options = optionDTOs.stream().map(optionDTO -> {
                                                OptionValue finalOptionValue;
                                                String finalAnswerText;

                                                if (questionDTO.getAnswerType() == AnswerType.MULTI_CHOICE) {
                                                    finalOptionValue = OptionValue.DEFAULT_ANSWER;
                                                    finalAnswerText = optionDTO.getAnswerText(); // use user input
                                                } else {
                                                    finalOptionValue = optionDTO.getOptionValue(); // enum value
                                                    finalAnswerText = String.valueOf(OptionValue.CUSTOM_ANSWER); // static string
                                                }

                                                String key = finalOptionValue.name() + "::" + finalAnswerText;

                                                if (optionCache.containsKey(key)) {
                                                    return optionCache.get(key);
                                                }

                                                Optional<AnswerOption> existingOption = answerOptionRepository
                                                        .findByOptionValueAndAnswerText(finalOptionValue, finalAnswerText);

                                                AnswerOption option;
                                                if (existingOption.isPresent()) {
                                                    option = existingOption.get();
                                                } else {
                                                    option = new AnswerOption();
                                                    option.setOptionValue(finalOptionValue);
                                                    option.setAnswerText(finalAnswerText);
                                                    option.setScore(optionDTO.getScore());
                                                    option = answerOptionRepository.save(option);
                                                }

                                                optionCache.put(key, option);
                                                return option;
                                            }).collect(Collectors.toList());
                                        }

                                        question.setAnswerOptions(new HashSet<>(options));
                                        return question;
                                    }).collect(Collectors.toList());

                            section.setQuestions(questions);
                        }

                        if (sectionDTO.getSectionInterpretation() != null) {
                            List<SectionInterpretation> interpretations = sectionDTO.getSectionInterpretation().stream()
                                    .map(interpDTO -> {
                                        SectionInterpretation interp = new SectionInterpretation();
                                        interp.setMinScore(interpDTO.getMinScore());
                                        interp.setMaxScore(interpDTO.getMaxScore());
                                        interp.setDescription(interpDTO.getDescription());
                                        interp.setSection(section);
                                        return interp;
                                    }).collect(Collectors.toList());

                            section.setSectionInterpretations(interpretations);
                        }

                        return section;
                    }).collect(Collectors.toList());

            test.setSections(sections);
        }

        if (dto.getInterpretations() != null) {
            List<Interpretation> interpretations = dto.getInterpretations().stream()
                    .map(interpDTO -> {
                        Interpretation interp = new Interpretation();
                        interp.setMinScore(interpDTO.getMinScore());
                        interp.setMaxScore(interpDTO.getMaxScore());
                        interp.setDescription(interpDTO.getDescription());
                        interp.setTest(test);
                        return interp;
                    }).collect(Collectors.toList());

            test.setInterpretations(interpretations);
        }

        if (dto.getRecommendations() != null) {
            List<Recommendation> recommendations = dto.getRecommendations().stream()
                    .map(recDTO -> {
                        Recommendation rec = new Recommendation();
                        rec.setMinScore(recDTO.getMinScore());
                        rec.setMaxScore(recDTO.getMaxScore());
                        rec.setRecommendationText(recDTO.getRecommendationText());
                        rec.setTest(test);
                        return rec;
                    }).collect(Collectors.toList());

            test.setRecommendations(recommendations);
        }

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
                sectionDTO.setSectionInterpretationResponse(sectionInterpDTOs);
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