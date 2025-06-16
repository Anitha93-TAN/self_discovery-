package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserTestResponseDTO {
    private Long testId;
    private String testTitle;
    private String description;
    private LocalDate linkExpiryDate;
}
