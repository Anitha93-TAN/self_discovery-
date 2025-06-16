package com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.user.UserTest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTestResultDTO {
    private int totalScore;
    private Map<String, Integer> sectionScores; // Section name → score
    private String interpretation;
    private String recommendation;
}
