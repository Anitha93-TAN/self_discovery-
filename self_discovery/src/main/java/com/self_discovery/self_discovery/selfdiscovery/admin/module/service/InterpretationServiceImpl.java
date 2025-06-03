package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.InterpretationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.InterpretationResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Interpretation;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.InterpretationRepository;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterpretationServiceImpl implements InterpretationService {

    @Autowired
    private InterpretationRepository interpretationRepository;

    @Autowired
    private TestRepository testRepository;

    @Override
    public InterpretationResponseDTO createInterpretation(InterpretationRequestDTO requestDTO) {
        Optional<Test> optionalTest = testRepository.findById(requestDTO.getTestId());
        if (optionalTest.isEmpty()) {
            throw new RuntimeException("Test not found with ID: " + requestDTO.getTestId());
        }

        Interpretation interpretation = new Interpretation();
        interpretation.setTest(optionalTest.get());
        interpretation.setMinScore(requestDTO.getMinScore());
        interpretation.setMaxScore(requestDTO.getMaxScore());
        interpretation.setDescription(requestDTO.getDescription());

        Interpretation saved = interpretationRepository.save(interpretation);

        return new InterpretationResponseDTO(
                saved.getInterpretationId(),
                saved.getTest().getTestId(),
                saved.getTest().getTitle(),
                saved.getMinScore(),
                saved.getMaxScore(),
                saved.getDescription()
        );
    }
}