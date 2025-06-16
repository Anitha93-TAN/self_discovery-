package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.interpretation.service.interfaces.IInterpretationService;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
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
@RequestMapping("/api/v1/admin/interpretations")
public class InterpretationController {

    @Autowired
    private IInterpretationService interpretationService;

    @Operation(summary = "Get all interpretations")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<InterpretationUpdateResponseDTO>>> getAll() {
        return ResponseEntity.status(HttpStatusCodes.OK).body(interpretationService.getAll());
    }

    @Operation(summary = "Get interpretation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InterpretationUpdateResponseDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatusCodes.OK).body(interpretationService.getById(id));
    }

    @Operation(summary = "Update interpretation by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InterpretationUpdateResponseDTO>> update(
            @PathVariable Long id,
            @RequestBody InterpretationUpdateRequestDTO dto) {
        return ResponseEntity.status(HttpStatusCodes.OK).body(interpretationService.update(id,dto));
    }

    @Operation(summary = "Delete all interpretations")
    @DeleteMapping("/deleteall")
    public ResponseEntity<ApiResponse<Void>> deleteAll() {
        return ResponseEntity.status(HttpStatusCodes.OK).body(interpretationService.deleteAll());
    }

    @Operation(summary = "Delete interpretation by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatusCodes.OK).body(interpretationService.deleteById(id));
    }
}
