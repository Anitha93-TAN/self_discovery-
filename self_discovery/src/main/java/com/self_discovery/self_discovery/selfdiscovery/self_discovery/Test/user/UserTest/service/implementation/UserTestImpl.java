package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.service.implementation;

import com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler.CustomException;
import com.self_discovery.self_discovery.selfdiscovery.repository.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserAnswerRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserTestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserTestResultDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.service.interfaces.IUserTest;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.QuestionResponse;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.SectionResponse;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.TestResult;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import com.self_discovery.self_discovery.selfdiscovery.utils.ResponseHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTestImpl implements IUserTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private SectionResponseRepository sectionResponseRepository;

    @Autowired
    private QuestionResponseRepository questionResponseRepository;

    @Autowired
    private UserTestRespository userTestRespository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private ResponseHandler<List<UserTestResponseDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<TestResponseDTO> testResponseHandler;

    @Override
    @Transactional
    public ApiResponse<List<UserTestResponseDTO>> getAllTests() {
        List<Test> tests = testRepository.findAll();
        List<UserTestResponseDTO> responseDTOs = tests.stream()
                .map(test -> {
                    UserTestResponseDTO dto = new UserTestResponseDTO();
                    dto.setTestId(test.getTestId());
                    dto.setTestTitle(test.getTestTitle());
                    dto.setDescription(test.getDescription());
                    dto.setLinkExpiryDate(test.getLinkExpiryDate());
                    return dto;
                })
                .collect(Collectors.toList());
        return listResponseHandler.success(responseDTOs, "All tests fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    @Transactional
    public ApiResponse<TestResponseDTO> getTestById(Long testId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new CustomException("Test not found with ID: " + testId));
        TestResponseDTO dto = mapToTestResponseDTO(test);
        return testResponseHandler.success(dto, "Test fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    @Transactional
    public UserTestResultDTO submitTestAndCalculateResult(UserAnswerRequestDTO requestDTO) {
        int totalScore = 0;
        Map<String, Integer> sectionScores = new HashMap<>();
        Map<Long, Integer> sectionIdToScore = new HashMap<>();
        Map<Long, String> sectionIdToTitle = new HashMap<>();

        for (UserAnswerRequestDTO answerDTO : requestDTO.getUserAnswers()) {
            Question question = questionRepository.findById(answerDTO.getQuestionId())
                    .orElseThrow(() -> new CustomException("Question not found with ID: " + answerDTO.getQuestionId()));

            AnswerOption selectedOption = question.getAnswerOptions().stream()
                    .filter(opt -> opt.getOptionValue() == answerDTO.getOptionValue())
                    .findFirst()
                    .orElseThrow(() -> new CustomException("Invalid option selected"));

            QuestionResponse qRes = new QuestionResponse();
            qRes.setQuestion(question);
            qRes.setAnswerOption(selectedOption);
            qRes.setRatingValue(selectedOption.getScore());
            questionResponseRepository.save(qRes);

            Section section = question.getSection(); // ✅ FIXED: Get section from question
            int score = selectedOption.getScore();

            sectionIdToScore.merge(section.getSectionId(), score, Integer::sum);
            sectionIdToTitle.putIfAbsent(section.getSectionId(), section.getSectionTitle());
            totalScore += score;
        }

        for (Map.Entry<Long, Integer> entry : sectionIdToScore.entrySet()) {
            Long sectionId = entry.getKey();
            int sectionScore = entry.getValue();

            Section section = sectionRepository.findById(sectionId)
                    .orElseThrow(() -> new CustomException("Section not found with ID: " + sectionId));

            String interpretation = section.getSectionInterpretations().stream()
                    .filter(i -> sectionScore >= i.getMinScore() && sectionScore <= i.getMaxScore())
                    .findFirst()
                    .map(SectionInterpretation::getDescription)
                    .orElse("No interpretation");

            SectionResponse sectionResponse = new SectionResponse();
            sectionResponse.setSection(section);
            sectionResponse.setSectionScore(sectionScore);
            sectionResponseRepository.save(sectionResponse);

            sectionScores.put(sectionIdToTitle.get(sectionId), sectionScore);
        }

        // Get the test from any section
        Long firstSectionId = sectionIdToScore.keySet().iterator().next();
        Section anySection = sectionRepository.findById(firstSectionId)
                .orElseThrow(() -> new CustomException("Section not found"));
        Test test = anySection.getTest();

        int finalTotalScore = totalScore;
        String testInterpretation = test.getInterpretations().stream()
                .filter(i -> finalTotalScore >= i.getMinScore() && finalTotalScore <= i.getMaxScore())
                .findFirst()
                .map(Interpretation::getDescription)
                .orElse("No overall interpretation");

        int finalTotalScore1 = totalScore;
        String recommendation = test.getRecommendations().stream()
                .filter(r -> finalTotalScore1 >= r.getMinScore() && finalTotalScore1 <= r.getMaxScore())
                .findFirst()
                .map(Recommendation::getRecommendationText)
                .orElse("No recommendation");

        TestResult result = new TestResult();
        result.setTest(test);
        result.setTotalScore(totalScore);
        testResultRepository.save(result);

        return new UserTestResultDTO(totalScore, sectionScores, testInterpretation, recommendation);
    }



    private TestResponseDTO mapToTestResponseDTO(Test test) {
        TestResponseDTO dto = new TestResponseDTO();
        dto.setTestId(test.getTestId());
        dto.setTestTitle(test.getTestTitle());
        dto.setDescription(test.getDescription());
        dto.setLinkExpiryDate(test.getLinkExpiryDate());

        if (test.getSections() != null) {
            dto.setSections(test.getSections().stream()
                    .map(this::mapToSectionResponseDTO)
                    .collect(Collectors.toList()));
        }

        if (test.getInterpretations() != null) {
            dto.setInterpretations(test.getInterpretations().stream()
                    .map(this::mapToInterpretationResponseDTO)
                    .collect(Collectors.toList()));
        }

        if (test.getRecommendations() != null) {
            dto.setRecommendations(test.getRecommendations().stream()
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
