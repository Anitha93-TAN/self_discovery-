package com.self_discovery.self_discovery.selfdiscovery.registration.repository;

import com.self_discovery.self_discovery.selfdiscovery.registration.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
