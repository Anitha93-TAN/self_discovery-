package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;

import java.util.List;

public interface IQuestionService {
    ApiResponse<List<QuestionUpdateResponseDTO>> getAll();
    ApiResponse<QuestionUpdateResponseDTO> getById(Long id);
    ApiResponse<QuestionUpdateResponseDTO> update(Long id, QuestionUpdateRequestDTO dto);
    ApiResponse<Void> deleteAll();
    ApiResponse<Void> deleteById(Long id);
}
