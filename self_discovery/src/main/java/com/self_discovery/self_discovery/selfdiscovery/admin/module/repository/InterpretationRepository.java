package com.self_discovery.self_discovery.selfdiscovery.admin.module.repository;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Interpretation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterpretationRepository extends JpaRepository<Interpretation, Integer> {
}
