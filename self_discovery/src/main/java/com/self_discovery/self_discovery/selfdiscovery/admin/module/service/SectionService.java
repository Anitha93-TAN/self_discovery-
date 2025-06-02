package com.self_discovery.self_discovery.selfdiscovery.admin.module.service;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionRequestDto;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Section;

public interface SectionService {
    Section createSection(SectionRequestDto dto);
}
