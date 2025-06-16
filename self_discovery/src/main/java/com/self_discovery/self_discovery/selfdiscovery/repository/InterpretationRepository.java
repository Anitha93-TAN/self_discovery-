package com.self_discovery.self_discovery.selfdiscovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.Interpretation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {

    @Query("SELECT i FROM Interpretation i WHERE i.test.id = :testId AND :score BETWEEN i.minScore AND i.maxScore")
    Interpretation findByTestIdAndScoreRange(@Param("testId") Long testId, @Param("score") int score);
}
