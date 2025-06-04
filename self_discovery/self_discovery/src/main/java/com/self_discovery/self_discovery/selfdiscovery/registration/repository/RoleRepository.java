package com.self_discovery.self_discovery.selfdiscovery.registration.repository;

import com.self_discovery.self_discovery.selfdiscovery.registration.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Optional custom queries
    Role findByRoleCode(String roleCode);
}
