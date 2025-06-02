package com.self_discovery.self_discovery.selfdiscovery.admin.domain.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TestRequestDTO {
    private String title;
    private String description;
    private LocalDate linkExpiryDate;
}
