package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.recommendation.service.interfaces.IRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final IRecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<List<RecommendationUpdateResponseDTO>> getAll() {
        return ResponseEntity.ok(recommendationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationUpdateResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(recommendationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecommendationUpdateResponseDTO> update(
            @PathVariable Long id,
            @RequestBody RecommendationUpdateRequestDTO dto) {
        return ResponseEntity.ok(recommendationService.update(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        recommendationService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        recommendationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}