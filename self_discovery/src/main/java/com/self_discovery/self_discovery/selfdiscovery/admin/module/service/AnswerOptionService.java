package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.AnswerOptionResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.AnswerOption;

public interface AnswerOptionService {
    AnswerOption save(AnswerOptionRequestDTO dto);
}
