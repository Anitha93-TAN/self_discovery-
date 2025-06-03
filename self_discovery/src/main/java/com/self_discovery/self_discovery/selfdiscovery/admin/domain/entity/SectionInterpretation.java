package com.self_discovery.self_discovery.selfdiscovery.admin.domain.entity;



import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section_interpretation")
public class SectionInterpretation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_interpretation_id")
    private Long sectionInterpretationId;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name = "min_score", nullable = false)
    private int minScore;

    @Column(name = "max_score", nullable = false)
    private int maxScore;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
}
