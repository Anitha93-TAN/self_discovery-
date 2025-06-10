package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.interfaces.ISectionInterpretationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sectionInterpretation")
@RequiredArgsConstructor
public class SectionInterpretationController {

    private final ISectionInterpretationService sectionInterpretationService;

    @GetMapping
    public ResponseEntity<List<SectionInterpretationUpdateResponseDTO>> getAll() {
        return ResponseEntity.ok(sectionInterpretationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionInterpretationUpdateResponseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sectionInterpretationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionInterpretationUpdateResponseDTO> update(
            @PathVariable("id") Long id,
            @RequestBody SectionInterpretationUpdateRequestDTO dto) {
        return ResponseEntity.ok(sectionInterpretationService.update(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        sectionInterpretationService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        sectionInterpretationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
