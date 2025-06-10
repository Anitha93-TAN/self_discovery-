package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.interfaces.IInterpretationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/interpretations")
@RequiredArgsConstructor
public class InterpretationController {

    private final IInterpretationService interpretationService;

    @GetMapping
    public ResponseEntity<List<InterpretationUpdateResponseDTO>> getAll() {
        return ResponseEntity.ok(interpretationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterpretationUpdateResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(interpretationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterpretationUpdateResponseDTO> update(
            @PathVariable Long id,
            @RequestBody InterpretationUpdateRequestDTO dto) {
        return ResponseEntity.ok(interpretationService.update(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        interpretationService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        interpretationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
