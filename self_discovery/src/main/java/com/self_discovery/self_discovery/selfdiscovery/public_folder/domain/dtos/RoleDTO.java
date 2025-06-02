package com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private String roleName;
    private String roleCode;
    private String description;
}