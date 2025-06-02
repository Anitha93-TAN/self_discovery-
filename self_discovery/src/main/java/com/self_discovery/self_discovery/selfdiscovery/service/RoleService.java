package com.self_discovery.self_discovery.selfdiscovery.service;

import com.self_discovery.self_discovery.selfdiscovery.domain.dtos.RoleDTO;
import com.self_discovery.self_discovery.selfdiscovery.domain.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(RoleDTO roleDTO);
    List<Role> getAllRoles();
    Role getRoleById(int id);
    Role updateRole(int id, RoleDTO roleDTO);
    void deleteRole(int id);
}
