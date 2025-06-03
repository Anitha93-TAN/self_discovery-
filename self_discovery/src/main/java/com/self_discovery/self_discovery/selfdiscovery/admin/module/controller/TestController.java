package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.TestService;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private final TestService testService;

    // Create Test endpoint
    @PostMapping
    public ResponseEntity<ApiResponse<TestResponseDTO>> createTest(@RequestBody TestRequestDTO testRequestDTO) {
        ApiResponse<TestResponseDTO> response = testService.createTest(testRequestDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // You can add more endpoints here like getTest, updateTest, deleteTest, etc.
}
