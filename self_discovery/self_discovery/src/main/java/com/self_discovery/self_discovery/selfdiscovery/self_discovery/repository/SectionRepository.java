package com.self_discovery.self_discovery.selfdiscovery.self_discovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
}
