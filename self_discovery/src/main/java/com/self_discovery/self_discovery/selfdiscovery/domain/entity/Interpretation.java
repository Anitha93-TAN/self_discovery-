package com.self_discovery.self_discovery.selfdiscovery.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interpretation")
public class Interpretation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interpretation_id")
    private int interpretationId;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "min_score", nullable = false)
    private int minScore;

    @Column(name = "max_score", nullable = false)
    private int maxScore;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
}
