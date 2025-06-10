package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.Test.dtos;

import lombok.Data;
import java.time.LocalDate;


@Data
public class TestUpdateResponseDTO {
    private Long testId;
    private String testTitle;
    private String description;
    private LocalDate linkExpiryDate;
}


