package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.TestService;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping
    public ResponseEntity<Test> createTest(@RequestBody TestRequestDTO testDto) {
        Test createdTest = testService.createTest(testDto);
        return ResponseEntity.ok(createdTest);
    }
    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }
}

