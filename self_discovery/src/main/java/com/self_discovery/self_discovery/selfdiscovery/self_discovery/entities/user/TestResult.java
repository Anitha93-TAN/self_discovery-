package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.Test;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "test_result")
public class TestResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultId;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

     @Column(name = "user_id")
    private Integer userId;

    @Column(name = "total_score", nullable = false)
    private Integer totalScore;

}
