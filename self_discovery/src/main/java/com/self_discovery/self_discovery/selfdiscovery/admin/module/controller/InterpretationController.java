package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.InterpretationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.InterpretationResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.InterpretationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interpretation")
public class InterpretationController {

    @Autowired
    private InterpretationService interpretationService;

    @PostMapping
    public InterpretationResponseDTO create(@RequestBody InterpretationRequestDTO requestDTO) {
        return interpretationService.createInterpretation(requestDTO);
    }
}
