package com.self_discovery.self_discovery.selfdiscovery.registration.service;

import com.self_discovery.self_discovery.selfdiscovery.registration.domain.dtos.RoleDTO;
import com.self_discovery.self_discovery.selfdiscovery.registration.domain.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(RoleDTO roleDTO);
    List<Role> getAllRoles();
    Role getRoleById(int id);
    Role updateRole(int id, RoleDTO roleDTO);
    void deleteRole(int id);
}
