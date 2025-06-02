package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionInterpretationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionInterpretationResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.SectionInterpretationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/section-interpretation")
public class SectionInterpretationController {

    @Autowired
    private SectionInterpretationService sectionInterpretationService;

    @PostMapping
    public SectionInterpretationResponseDTO create(@RequestBody SectionInterpretationRequestDTO requestDTO) {
        return sectionInterpretationService.createSectionInterpretation(requestDTO);
    }
}