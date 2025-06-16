package com.self_discovery.self_discovery.selfdiscovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.SectionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionResponseRepository extends JpaRepository<SectionResponse, Long> {
}
