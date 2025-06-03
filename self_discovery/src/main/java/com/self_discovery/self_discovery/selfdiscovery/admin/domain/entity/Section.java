package com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section")
public class Section extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private int sectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "section_order", nullable = false)
    private int sectionOrder;

    @Column(name = "randomize_questions", nullable = false)
    private boolean randomizeQuestions = false;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "section")
    private List<SectionInterpretation> sectionInterpretations;

}
