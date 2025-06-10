package com.self_discovery.self_discovery.selfdiscovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.SectionUpdateRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.Test.admin.section.dtos.SectionUpdateResponseDTO;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

}
