package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.AnswerOptionService;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.AnswerOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer-options")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionService answerOptionService;
    @PostMapping
    public ResponseEntity<AnswerOption> createAnswerOption(@RequestBody AnswerOptionRequestDTO dto) {
        AnswerOption saved = answerOptionService.save(dto);
        return ResponseEntity.ok(saved);

    }
}
