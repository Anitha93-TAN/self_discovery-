package com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.service;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.dtos.RoleDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.entity.Role;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleCode(roleDTO.getRoleCode());
        role.setDescription(roleDTO.getDescription());
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Override
    public Role updateRole(int id, RoleDTO roleDTO) {
        Role role = getRoleById(id);
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleCode(roleDTO.getRoleCode());
        role.setDescription(roleDTO.getDescription());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        Role role = getRoleById(id);
        roleRepository.delete(role);
    }
}
