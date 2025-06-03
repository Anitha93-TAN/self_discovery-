package com.self_discovery.self_discovery.selfdiscovery.admin.module.repository;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}