package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.QuestionResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserAnswerRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserTestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserTestResultDTO;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface IUserTest {
    ApiResponse<List<UserTestResponseDTO>> getAllTests();
    ApiResponse<TestResponseDTO> getTestById(Long testId);
    UserTestResultDTO submitTestAndCalculateResult(UserAnswerRequestDTO requestDTO);

}
