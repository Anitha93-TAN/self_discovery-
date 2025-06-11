package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.sectioninterpretation.service.interfaces.ISectionInterpretationService;
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
@RequestMapping("/api/v1/admin/sectioninterpretations")
public class SectionInterpretationController {

    @Autowired
    private ISectionInterpretationService sectionInterpretationService;


    @Operation(summary = "Get all section interpretations")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<SectionInterpretationUpdateResponseDTO>>> getAll() {
        ApiResponse<List<SectionInterpretationUpdateResponseDTO>> response = sectionInterpretationService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get section interpretation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SectionInterpretationUpdateResponseDTO>> getById(@PathVariable("id") Long id) {
        ApiResponse<SectionInterpretationUpdateResponseDTO> response = sectionInterpretationService.getById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update section interpretation by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SectionInterpretationUpdateResponseDTO>> update(
            @PathVariable("id") Long id,
            @RequestBody SectionInterpretationUpdateRequestDTO dto) {
        ApiResponse<SectionInterpretationUpdateResponseDTO> response = sectionInterpretationService.update(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete all section interpretations")
    @DeleteMapping("/deleteall")
    public ResponseEntity<ApiResponse<Void>> deleteAll() {
        ApiResponse<Void> response = sectionInterpretationService.deleteAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete section interpretation by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable("id") Long id) {
        ApiResponse<Void> response = sectionInterpretationService.deleteById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
