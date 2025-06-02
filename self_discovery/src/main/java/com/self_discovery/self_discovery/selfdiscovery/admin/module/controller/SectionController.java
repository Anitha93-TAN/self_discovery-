package com.self_discovery.self_discovery.selfdiscovery.admin.module.controller;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto.SectionRequestDto;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.admin.module.service.SectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    @Autowired
    private SectionServiceImpl sectionService;

    @PostMapping
    public Section createSection(@RequestBody SectionRequestDto dto) {

        return sectionService.createSection(dto);
    }
}
