package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.QuestionRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Question;

public interface QuestionService {
    Question createQuestion(QuestionRequestDTO questionRequestDTO);
}
