package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.service.service;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface TestService {
    ApiResponse<TestResponseDTO> createTest(TestRequestDTO requestDto);
    ApiResponse<List<TestResponseDTO>> getAllTests();

}
