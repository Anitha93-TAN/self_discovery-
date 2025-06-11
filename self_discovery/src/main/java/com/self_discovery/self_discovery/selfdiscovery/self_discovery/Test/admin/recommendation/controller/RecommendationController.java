package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.interfaces.IRecommendationService;
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
@RequestMapping("/api/v1/admin/recommendations")
public class RecommendationController {

    @Autowired
    private IRecommendationService recommendationService;

    @Operation(summary = "Get all recommendations")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<RecommendationUpdateResponseDTO>>> getAll() {
        ApiResponse<List<RecommendationUpdateResponseDTO>> response = recommendationService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get recommendation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecommendationUpdateResponseDTO>> getById(@PathVariable Long id) {
        ApiResponse<RecommendationUpdateResponseDTO> response = recommendationService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update recommendation by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RecommendationUpdateResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody RecommendationUpdateRequestDTO dto) {
        ApiResponse<RecommendationUpdateResponseDTO> response = recommendationService.update(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete all recommendations")
    @DeleteMapping("/deleteall")
    public ResponseEntity<ApiResponse<Void>> deleteAll() {
        ApiResponse<Void> response = recommendationService.deleteAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete recommendation by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        ApiResponse<Void> response = recommendationService.deleteById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
