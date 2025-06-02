package com.self_discovery.self_discovery.selfdiscovery.admin.module.repository;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {
}