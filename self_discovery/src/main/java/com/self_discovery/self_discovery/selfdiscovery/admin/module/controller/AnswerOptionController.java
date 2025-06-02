package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.AnswerOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer-options")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionService answerOptionService;

    @PostMapping
    public AnswerOptionResponseDTO createAnswerOption(@RequestBody AnswerOptionRequestDTO requestDTO) {
        return answerOptionService.createAnswerOption(requestDTO);
    }
}