package com.self_discovery.self_discovery.selfdiscovery.repository;


import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}