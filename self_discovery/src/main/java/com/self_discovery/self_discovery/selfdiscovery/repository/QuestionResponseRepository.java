package com.self_discovery.self_discovery.selfdiscovery.repository;


import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {
}
