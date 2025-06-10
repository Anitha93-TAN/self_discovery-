package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.TestUpdateRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.TestUpdateResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.service.interfaces.TestService;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @Operation(summary = "Create a test with sections, questions, and answer options")
    @PostMapping("/section/question/answeroption")
    public ResponseEntity<ApiResponse<TestResponseDTO>> createTest(@Valid @RequestBody TestRequestDTO testRequestDTO) {
        ApiResponse<TestResponseDTO> response = testService.createTest(testRequestDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get all tests")
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<TestResponseDTO>>> getAllTests() {
        ApiResponse<List<TestResponseDTO>> response = testService.getAllTests();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get test by ID")
    @GetMapping("/{testId}")
    public ResponseEntity<ApiResponse<TestResponseDTO>> getTestById(@PathVariable Long testId) {
        ApiResponse<TestResponseDTO> response = testService.getTestById(testId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update a test by ID")
    @PutMapping("/{testId}")
    public ResponseEntity<ApiResponse<TestUpdateResponseDTO>> updateTest(
            @PathVariable Long testId,
            @Valid @RequestBody TestUpdateRequestDTO testRequestDTO) {
        ApiResponse<TestUpdateResponseDTO> response = testService.updateTest(testId, testRequestDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete a test by ID")
    @DeleteMapping("/{testId}")
    public ResponseEntity<ApiResponse<Void>> deleteTestById(@PathVariable Long testId) {
        ApiResponse<Void> response = testService.deleteTestById(testId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete all tests")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllTests() {
        ApiResponse<Void> response = testService.deleteAllTests();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
