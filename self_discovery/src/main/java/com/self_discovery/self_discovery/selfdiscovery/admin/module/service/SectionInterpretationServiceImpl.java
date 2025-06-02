package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionInterpretationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionInterpretationResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.SectionInterpretation;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.SectionInterpretationRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SectionInterpretationServiceImpl implements SectionInterpretationService {

    @Autowired
    private SectionInterpretationRepository sectionInterpretationRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public SectionInterpretationResponseDTO createSectionInterpretation(SectionInterpretationRequestDTO requestDTO) {
        Optional<Section> optionalSection = sectionRepository.findById(requestDTO.getSectionId());
        if (optionalSection.isEmpty()) {
            throw new RuntimeException("Section not found with ID: " + requestDTO.getSectionId());
        }

        SectionInterpretation entity = new SectionInterpretation();
        entity.setSection(optionalSection.get());
        entity.setMinScore(requestDTO.getMinScore());
        entity.setMaxScore(requestDTO.getMaxScore());
        entity.setDescription(requestDTO.getDescription());

        SectionInterpretation saved = sectionInterpretationRepository.save(entity);

        return new SectionInterpretationResponseDTO(
                saved.getSectionInterpretationId(),
                saved.getSection().getSectionId(),
                //saved.getSection().getSectionName(),
                saved.getSection().getTitle(),
                saved.getMinScore(),
                saved.getMaxScore(),
                saved.getDescription()
        );
    }
}