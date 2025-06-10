package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service.interfaces.ISectionService;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@RequiredArgsConstructor
public class SectionController {

    private final ISectionService sectionService;

    @GetMapping
    public ResponseEntity<List<SectionUpdateResponseDTO>> getAllSections() {
        return ResponseEntity.ok(sectionService.getAllSections());
    }

    @GetMapping("/{sectionId}")
    public ResponseEntity<SectionUpdateResponseDTO> getSectionById(@PathVariable Long sectionId) {
        return ResponseEntity.ok(sectionService.getSectionById(sectionId));
    }

    @PutMapping("/{sectionId}")
    public ResponseEntity<SectionUpdateResponseDTO> updateSection(
            @PathVariable Long sectionId,
            @RequestBody SectionUpdateRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(sectionService.updateSection(sectionId, requestDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllSections() {
        sectionService.deleteAllSections();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sectionId}")
    public ResponseEntity<Void> deleteSectionById(@PathVariable Long sectionId) {
        sectionService.deleteSectionById(sectionId);
        return ResponseEntity.noContent().build();
    }
}