package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TestService {
    ApiResponse<TestResponseDTO> createTest(TestRequestDTO requestDto);
    ApiResponse<List<TestResponseDTO>> getAllTests();

}
