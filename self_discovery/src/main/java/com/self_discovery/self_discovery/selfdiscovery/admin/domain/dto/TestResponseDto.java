package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResponseDto {
    private String title;
    private String description;
    private LocalDate linkExpiryDate;
    private List<SectionRequestDto> sections;
}

