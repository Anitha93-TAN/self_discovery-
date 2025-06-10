package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.TestUpdateRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.TestUpdateResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import java.util.List;

public interface TestService {
    ApiResponse<TestResponseDTO> createTest(TestRequestDTO requestDto);
    ApiResponse<List<TestResponseDTO>> getAllTests();
    ApiResponse<TestResponseDTO> getTestById(Long testId);
    ApiResponse<TestUpdateResponseDTO> updateTest(Long testId, TestUpdateRequestDTO requestDto);
    ApiResponse<Void> deleteTestById(Long testId);
    ApiResponse<Void> deleteAllTests();

}
