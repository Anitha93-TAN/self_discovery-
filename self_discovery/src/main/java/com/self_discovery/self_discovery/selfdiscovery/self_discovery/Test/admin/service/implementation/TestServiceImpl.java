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
                                    finalOptionValue = OptionValue.DEFAULT_ANSWER; // multi-choice க்கான fixed enum
                                    finalAnswerText = optionDTO.getAnswerText();  // user input text
                                } else {
                                    finalOptionValue = optionDTO.getOptionValue(); // enum from DTO
                                    finalAnswerText = OptionValue.CUSTOM_ANSWER.name(); // fixed string
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

                                options.add(option);
                            }
                        }

                        question.setAnswerOptions(new HashSet<>(options));
                        questions.add(question);
                        section.setQuestions(questions);
                    }
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

        if (entity.getSections() != null) {
            dto.setSections(entity.getSections().stream()
                    .map(this::mapToSectionResponseDTO)
                    .collect(Collectors.toList()));
        }

        if (entity.getInterpretations() != null) {
            dto.setInterpretations(entity.getInterpretations().stream()
                    .map(this::mapToInterpretationResponseDTO)
                    .collect(Collectors.toList()));
        }

        if (entity.getRecommendations() != null) {
            dto.setRecommendations(entity.getRecommendations().stream()
                    .map(this::mapToRecommendationResponseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private SectionResponseDTO mapToSectionResponseDTO(Section section) {
        SectionResponseDTO dto = new SectionResponseDTO();
        dto.setSectionTitle(section.getSectionTitle());
        dto.setSectionOrder(section.getSectionOrder());
        dto.setRandomizeQuestions(section.isRandomizeQuestions());

        if (section.getQuestions() != null) {
            dto.setQuestions(section.getQuestions().stream()
                    .map(this::mapToQuestionResponseDTO)
                    .collect(Collectors.toList()));
        }

        if (section.getSectionInterpretations() != null) {
            dto.setSectionInterpretationResponse(section.getSectionInterpretations().stream()
                    .map(this::mapToSectionInterpretationResponseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private QuestionResponseDTO mapToQuestionResponseDTO(Question question) {
        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setQuestionId(question.getQuestionId());
        dto.setQuestionText(question.getQuestionText());
        dto.setAnswerType(question.getAnswerType());
        dto.setQuestionOrder(question.getQuestionOrder());

        if (question.getAnswerOptions() != null) {
            dto.setAnswerOptions(question.getAnswerOptions().stream()
                    .map(this::mapToAnswerOptionResponseDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private AnswerOptionResponseDTO mapToAnswerOptionResponseDTO(AnswerOption option) {
        AnswerOptionResponseDTO dto = new AnswerOptionResponseDTO();
        dto.setAnswerOptionId(option.getAnswerOptionId());
        dto.setAnswerText(option.getAnswerText());
        dto.setOptionValue(option.getOptionValue());
        dto.setScore(option.getScore());
        return dto;
    }

    private SectionInterpretationResponseDTO mapToSectionInterpretationResponseDTO(SectionInterpretation si) {
        SectionInterpretationResponseDTO dto = new SectionInterpretationResponseDTO();
        dto.setSectionInterpretationId(si.getSectionInterpretationId());
        dto.setTitle(si.getTitle());
        dto.setMinScore(si.getMinScore());
        dto.setMaxScore(si.getMaxScore());
        dto.setDescription(si.getDescription());
        return dto;
    }

    private InterpretationResponseDTO mapToInterpretationResponseDTO(Interpretation interpretation) {
        InterpretationResponseDTO dto = new InterpretationResponseDTO();
        dto.setInterpretationId(interpretation.getInterpretationId());
        dto.setTitle(interpretation.getTitle());
        dto.setMinScore(interpretation.getMinScore());
        dto.setMaxScore(interpretation.getMaxScore());
        dto.setDescription(interpretation.getDescription());
        return dto;
    }

    private RecommendationResponseDTO mapToRecommendationResponseDTO(Recommendation recommendation) {
        RecommendationResponseDTO dto = new RecommendationResponseDTO();
        dto.setRecommendationId(recommendation.getRecommendationId());
        dto.setTitle(recommendation.getTitle());
        dto.setMinScore(recommendation.getMinScore());
        dto.setMaxScore(recommendation.getMaxScore());
        dto.setRecommendationText(recommendation.getRecommendationText());
        return dto;
    }

}
