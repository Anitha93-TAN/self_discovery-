package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.admin.Section;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "section_response")
public class SectionResponse extends BaseEntity {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_response_id")
    private Long sectionResponseId;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "section_score", nullable = false)
    private int sectionScore;

}
