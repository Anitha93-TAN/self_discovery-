package com.self_discovery.self_discovery.selfdiscovery.admin.module.repository;


import com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.admin.domain.enums.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {
    Optional<AnswerOption> findByOptionValue(OptionValue optionValue);
}