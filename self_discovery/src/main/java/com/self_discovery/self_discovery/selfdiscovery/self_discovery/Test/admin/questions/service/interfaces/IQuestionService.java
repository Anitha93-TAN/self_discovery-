package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.service.interfaces;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.questions.dtos.*;
import java.util.List;

public interface IQuestionService {
    List<QuestionUpdateResponseDTO> getAll();
    QuestionUpdateResponseDTO getById(Long id);
    QuestionUpdateResponseDTO update(Long id, QuestionUpdateRequestDTO dto);
    void deleteAll();
    void deleteById(Long id);
}