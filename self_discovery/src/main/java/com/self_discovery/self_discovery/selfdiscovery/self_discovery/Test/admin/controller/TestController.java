package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.service.service.TestService;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
}
