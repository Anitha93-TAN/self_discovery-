package com.self_discovery.self_discovery.selfdiscovery.admin.module.repository;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    // You can add custom query methods here if needed
}
