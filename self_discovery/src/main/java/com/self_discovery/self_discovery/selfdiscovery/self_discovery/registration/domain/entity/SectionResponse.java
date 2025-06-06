package com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.entity;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity.Section;
import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section_response")
public class SectionResponse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_response_id")
    private int sectionResponseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name = "section_score", nullable = false)
    private int sectionScore;

}
