package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionRequestDto;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.SectionRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private TestRepository testRepository;

    public Section createSection(SectionRequestDto dto) {
        Test test = testRepository.findById(dto.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found"));

        Section section = new Section();
        section.setTest(test);
        section.setTitle(dto.getTitle());
        section.setSectionOrder(dto.getSectionOrder());
        section.setRandomizeQuestions(dto.isRandomizeQuestions());

        return sectionRepository.save(section);
    }
}
