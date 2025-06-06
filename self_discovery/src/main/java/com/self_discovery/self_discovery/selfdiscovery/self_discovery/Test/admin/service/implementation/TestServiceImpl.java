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
        // Create Test entity (basic fields)
        Test test = mapToTestEntity(testRequestDTO);

        // Map sections, questions, options with caching and DB lookup for options
        if (testRequestDTO.getSections() != null) {
            List<Section> sections = new ArrayList<>();
            Map<String, AnswerOption> optionCache = new HashMap<>();

            for (SectionRequestDTO sectionDTO : testRequestDTO.getSections()) {
                Section section = mapToSectionEntity(sectionDTO);
                section.setTest(test);

                if (sectionDTO.getQuestions() != null) {
                    List<Question> questions = new ArrayList<>();
                    for (QuestionRequestDTO questionDTO : sectionDTO.getQuestions()) {
                        Question question = mapToQuestionEntity(questionDTO);
                        question.setSection(section);

                        List<AnswerOption> options = new ArrayList<>();
                        if (questionDTO.getAnswerOptions() != null) {
                            for (AnswerOptionRequestDTO optionDTO : questionDTO.getAnswerOptions()) {
                                OptionValue finalOptionValue;
                                String finalAnswerText;

                                if (questionDTO.getAnswerType() == AnswerType.MULTI_CHOICE) {
                                    finalOptionValue = OptionValue.DEFAULT_ANSWER; // use default enum for multi-choice
                                    finalAnswerText = optionDTO.getAnswerText();  // user input text
                                } else {
                                    finalOptionValue = optionDTO.getOptionValue(); // enum from DTO
                                    finalAnswerText = OptionValue.CUSTOM_ANSWER.name(); // static string
                                }

                                String key = finalOptionValue.name() + "::" + finalAnswerText;

                                AnswerOption option = optionCache.get(key);
                                if (option == null) {
                                    Optional<AnswerOption> existingOption = answerOptionRepository.findByOptionValueAndAnswerText(finalOptionValue, finalAnswerText);
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
                                }
                                options.add(option);
                            }
                        }
                        question.setAnswerOptions(new HashSet<>(options));
                        questions.add(question);
                    }
                    section.setQuestions(questions);
                }

                if (sectionDTO.getSectionInterpretation() != null) {
                    List<SectionInterpretation> interpretations = sectionDTO.getSectionInterpretation().stream()
                            .map(this::mapToSectionInterpretationEntity)
                            .collect(Collectors.toList());
                    interpretations.forEach(i -> i.setSection(section));
                    section.setSectionInterpretations(interpretations);
                }

                sections.add(section);
            }
            test.setSections(sections);
        }

        if (testRequestDTO.getInterpretations() != null) {
            List<Interpretation> interpretations = testRequestDTO.getInterpretations().stream()
                    .map(this::mapToInterpretationEntity)
                    .collect(Collectors.toList());
            interpretations.forEach(i -> i.setTest(test));
            test.setInterpretations(interpretations);
        }

        if (testRequestDTO.getRecommendations() != null) {
            List<Recommendation> recommendations = testRequestDTO.getRecommendations().stream()
                    .map(this::mapToRecommendationEntity)
                    .collect(Collectors.toList());
            recommendations.forEach(r -> r.setTest(test));
            test.setRecommendations(recommendations);
        }

        // Save the entire test with all nested entities
        Test savedTest = testRepository.save(test);

        // Map saved entity to response DTO
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

    // Pure mapping methods below (no repo calls or side effects)

    private Test mapToTestEntity(TestRequestDTO dto) {
        Test test = new Test();
        test.setTestTitle(dto.getTestTitle());
        test.setDescription(dto.getDescription());
        test.setLinkExpiryDate(dto.getLinkExpiryDate());
        return test;
    }

    private Section mapToSectionEntity(SectionRequestDTO dto) {
        Section section = new Section();
        section.setSectionTitle(dto.getSectionTitle());
        section.setSectionOrder(dto.getSectionOrder());
        section.setRandomizeQuestions(dto.isRandomizeQuestions());
        return section;
    }

    private Question mapToQuestionEntity(QuestionRequestDTO dto) {
        Question question = new Question();
        question.setQuestionText(dto.getQuestionText());
        question.setAnswerType(dto.getAnswerType());
        question.setQuestionOrder(dto.getQuestionOrder());
        return question;
    }

    private SectionInterpretation mapToSectionInterpretationEntity(SectionInterpretationRequestDTO dto) {
        SectionInterpretation si = new SectionInterpretation();
        si.setTitle(dto.getTitle());
        si.setMinScore(dto.getMinScore());
        si.setMaxScore(dto.getMaxScore());
        si.setDescription(dto.getDescription());
        return si;
    }

    private Interpretation mapToInterpretationEntity(InterpretationRequestDTO dto) {
        Interpretation interpretation = new Interpretation();
        interpretation.setTitle(dto.getTitle());
        interpretation.setMinScore(dto.getMinScore());
        interpretation.setMaxScore(dto.getMaxScore());
        interpretation.setDescription(dto.getDescription());
        return interpretation;
    }

    private Recommendation mapToRecommendationEntity(RecommendationRequestDTO dto) {
        Recommendation rec = new Recommendation();
        rec.setTitle(dto.getTitle());
        rec.setMinScore(dto.getMinScore());
        rec.setMaxScore(dto.getMaxScore());
        rec.setRecommendationText(dto.getRecommendationText());
        return rec;
    }

    private TestResponseDTO mapToTestResponseDTO(Test entity) {
        TestResponseDTO dto = new TestResponseDTO();
        dto.setTestId(entity.getTestId());
        dto.setTestTitle(entity.getTestTitle());
        dto.setDescription(entity.getDescription());
        dto.setLinkExpiryDate(entity.getLinkExpiryDate());

        List<SectionResponseDTO> sectionDTOs = new ArrayList<>();
        if (entity.getSections() != null) {
            for (Section section : entity.getSections()) {
                SectionResponseDTO sectionDTO = new SectionResponseDTO();
                sectionDTO.setSectionTitle(section.getSectionTitle());
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
                        siDTO.setTitle(si.getTitle());
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
                interpDTO.setTitle(interpretation.getTitle());
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
                recDTO.setTitle(recommendation.getTitle());
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
