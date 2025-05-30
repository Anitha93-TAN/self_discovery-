package com.self_discovery.self_discovery.selfdiscovery.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private int sectionId;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "order", nullable = false)
    private int order;

    @Column(name = "randomize_questions", nullable = false)
    private boolean randomizeQuestions = false;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Question> questions;
}
