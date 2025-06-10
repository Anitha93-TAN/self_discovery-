package com.self_discovery.self_discovery.selfdiscovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.AnswerOption;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.enums.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    Optional<AnswerOption> findByOptionValue(OptionValue optionValue);
}
