package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces.IQuestionService;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/questions")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @Operation(summary = "Get all questions")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<QuestionUpdateResponseDTO>>> getAll() {
        ApiResponse<List<QuestionUpdateResponseDTO>> response = questionService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get question by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionUpdateResponseDTO>> getById(@PathVariable Long id) {
        ApiResponse<QuestionUpdateResponseDTO> response = questionService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update question by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionUpdateResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody QuestionUpdateRequestDTO dto) {
        ApiResponse<QuestionUpdateResponseDTO> response = questionService.update(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete all questions")
    @DeleteMapping("/deleteall")
    public ResponseEntity<ApiResponse<Void>> deleteAll() {
        ApiResponse<Void> response = questionService.deleteAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete question by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        ApiResponse<Void> response = questionService.deleteById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
