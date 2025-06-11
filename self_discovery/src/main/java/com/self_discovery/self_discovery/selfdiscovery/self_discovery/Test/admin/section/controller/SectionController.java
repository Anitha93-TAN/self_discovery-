package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.service.interfaces.ISectionService;
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
@RequestMapping("/api/v1/admin/sections")
public class SectionController {

    @Autowired
    private ISectionService sectionService;

    @Operation(summary = "Get all sections")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<SectionUpdateResponseDTO>>> getAllSections() {
        ApiResponse<List<SectionUpdateResponseDTO>> response = sectionService.getAllSections();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get section by ID")
    @GetMapping("/{sectionId}")
    public ResponseEntity<ApiResponse<SectionUpdateResponseDTO>> getSectionById(@PathVariable Long sectionId) {
        ApiResponse<SectionUpdateResponseDTO> response = sectionService.getSectionById(sectionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update section by ID")
    @PutMapping("/{sectionId}")
    public ResponseEntity<ApiResponse<SectionUpdateResponseDTO>> updateSection(
            @PathVariable Long sectionId,
            @RequestBody SectionUpdateRequestDTO requestDTO) {
        ApiResponse<SectionUpdateResponseDTO> response = sectionService.updateSection(sectionId, requestDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete section by ID")
    @DeleteMapping("/{sectionId}")
    public ResponseEntity<ApiResponse<Void>> deleteSectionById(@PathVariable Long sectionId) {
        ApiResponse<Void> response = sectionService.deleteSectionById(sectionId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete all sections")
    @DeleteMapping("/deleteall")
    public ResponseEntity<ApiResponse<Void>> deleteAllSections() {
        ApiResponse<Void> response = sectionService.deleteAllSections();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
