package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.QuestionRequestDTO;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.QuestionRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Question createQuestion(QuestionRequestDTO dto) {
        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with ID: " + dto.getSectionId()));

        Question question = new Question();
        question.setSection(section);
        question.setQuestionText(dto.getQuestionText());
        question.setAnswerType(dto.getAnswerType());
        question.setQuestionOrder(dto.getQuestionOrder());

        return questionRepository.save(question);
    }
}
