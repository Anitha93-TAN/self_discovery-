package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionUpdateResponseDTO>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionUpdateResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionUpdateResponseDTO> update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDTO dto) {
        return ResponseEntity.ok(questionService.update(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        questionService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}