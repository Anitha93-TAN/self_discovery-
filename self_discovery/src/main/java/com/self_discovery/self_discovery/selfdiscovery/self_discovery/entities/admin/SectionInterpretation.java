package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin;



import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section_interpretation")
public class SectionInterpretation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_interpretation_id")
    private Long sectionInterpretationId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "min_score")
    private int minScore;

    @Column(name = "max_score")
    private int maxScore;

    @Column(name = "description")
    private String description;
}
