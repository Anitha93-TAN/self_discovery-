package com.self_discovery.self_discovery.selfdiscovery.admin.module.repository;

import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.SectionInterpretation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionInterpretationRepository extends JpaRepository<SectionInterpretation, Integer> {
}