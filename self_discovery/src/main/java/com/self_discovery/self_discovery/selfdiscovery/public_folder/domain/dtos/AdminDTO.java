package com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String roleCode;

}
