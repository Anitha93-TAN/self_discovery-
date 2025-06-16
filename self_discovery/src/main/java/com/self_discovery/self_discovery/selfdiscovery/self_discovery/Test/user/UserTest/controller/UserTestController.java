package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.controller;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos.TestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserAnswerRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserTestResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos.UserTestResultDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.service.interfaces.IUserTest;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/tests")
@RequiredArgsConstructor
public class UserTestController {

    private final IUserTest userTestService;

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<UserTestResponseDTO>>> getAllTests() {
        ApiResponse<List<UserTestResponseDTO>> response = userTestService.getAllTests();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/{testId}")
    public ResponseEntity<ApiResponse<TestResponseDTO>> getTestById(@PathVariable Long testId) {
        ApiResponse<TestResponseDTO> response = userTestService.getTestById(testId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/submit")
    public UserTestResultDTO submitTest(@RequestBody UserAnswerRequestDTO requestDTO) {
        return userTestService.submitTestAndCalculateResult(requestDTO);
    }
    }


