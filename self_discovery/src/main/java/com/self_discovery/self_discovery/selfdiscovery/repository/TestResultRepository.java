package com.self_discovery.self_discovery.selfdiscovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
