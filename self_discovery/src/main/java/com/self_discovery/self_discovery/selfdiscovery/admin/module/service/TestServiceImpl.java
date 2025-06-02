package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.TestRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public Test createTest(TestRequestDTO testDto) {
        Test test = new Test();
        test.setTitle(testDto.getTitle());
        test.setDescription(testDto.getDescription());
        test.setLinkExpiryDate(testDto.getLinkExpiryDate());
        return testRepository.save(test);
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
