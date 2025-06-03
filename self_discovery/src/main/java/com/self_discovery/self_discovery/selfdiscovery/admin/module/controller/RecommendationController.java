package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.RecommendationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.RecommendationResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public RecommendationResponseDTO create(@RequestBody RecommendationRequestDTO requestDTO) {
        return recommendationService.createRecommendation(requestDTO);
    }
}