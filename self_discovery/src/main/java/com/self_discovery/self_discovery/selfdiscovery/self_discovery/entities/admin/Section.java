package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin;


import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.SectionResponse;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section")
public class Section extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "section_title")
    private String sectionTitle;

    @Column(name = "section_order")
    private int sectionOrder;

    @Column(name = "randomize_questions")
    private boolean randomizeQuestions = false;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<SectionInterpretation> sectionInterpretations;

    @OneToMany(mappedBy = "section",cascade = CascadeType.ALL )
    private List<SectionResponse> sectionResponses;

}
