package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.service.TestService;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private final TestService testService;

    // Create Test endpoint
    @PostMapping
    public ResponseEntity<ApiResponse<TestResponseDTO>> createTest(@Valid @RequestBody TestRequestDTO testRequestDTO) {
        ApiResponse<TestResponseDTO> response = testService.createTest(testRequestDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<TestResponseDTO>>> getAllTests() {
        ApiResponse<List<TestResponseDTO>> response = testService.getAllTests();
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    // You can add more endpoints here like getTest, updateTest, deleteTest, etc.
}
