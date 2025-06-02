package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.QuestionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Question;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequestDTO dto) {
        Question savedQuestion = questionService.createQuestion(dto);
        return ResponseEntity.ok(savedQuestion);
    }
}
