package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;



import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;

import java.util.List;

public interface TestService {

    List<Test> getAllTests();
    Test createTest(TestRequestDTO testDto);
}
