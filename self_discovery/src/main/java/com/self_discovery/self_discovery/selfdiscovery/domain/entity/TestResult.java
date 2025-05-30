package com.self_discovery.self_discovery.selfdiscovery.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_result")
public class TestResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_score", nullable = false)
    private int totalScore;

}
