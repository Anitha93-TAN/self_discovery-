package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.*;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.TestRepository;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import com.self_discovery.self_discovery.selfdiscovery.utils.ResponseHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final ResponseHandler<TestResponseDTO> responseHandler;

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
                        try {
                            question.setAnswerType(questionDTO.getAnswerType());
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Invalid AnswerType: " + questionDTO.getAnswerType());
                        }
                        question.setQuestionOrder(questionDTO.getQuestionOrder());
                        question.setSection(section);

                        List<AnswerOption> options = new ArrayList<>();
                        if (questionDTO.getAnswerOptions() != null) {
                            for (AnswerOptionRequestDTO optionDTO : questionDTO.getAnswerOptions()) {
                                AnswerOption option = new AnswerOption();
                                option.setAnswerText(optionDTO.getAnswerText());
                                try {
                                    option.setOptionValue(optionDTO.getOptionValue());
                                } catch (IllegalArgumentException e) {
                                    throw new IllegalArgumentException("Invalid OptionValue: " + optionDTO.getOptionValue());
                                }
                                option.setScore(optionDTO.getScore());
                              //  option.setQuestion(question); // ✅ Fix: Maintain bidirectional mapping
                                options.add(option);
                            }
                        }
                        question.setAnswerOptions(new HashSet<>(options));
                        questions.add(question);
                    }
                }
                section.setQuestions(questions);

                List<SectionInterpretation> sectionInterpretations = new ArrayList<>();
                if (sectionDTO.getSectionInterpretation() != null) {
                    for (SectionInterpretationRequestDTO interpDTO : sectionDTO.getSectionInterpretation()) {
                        SectionInterpretation sectionInterpretation = new SectionInterpretation();
                        sectionInterpretation.setMinScore(interpDTO.getMinScore());
                        sectionInterpretation.setMaxScore(interpDTO.getMaxScore());
                        sectionInterpretation.setDescription(interpDTO.getDescription());
                        sectionInterpretation.setSection(section);
                        sectionInterpretations.add(sectionInterpretation);
                    }
                }
                section.setSectionInterpretations(sectionInterpretations);

                sections.add(section);
            }
        }
        test.setSections(sections);
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
        return dto;
    }
}
