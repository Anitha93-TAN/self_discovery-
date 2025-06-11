package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entity;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test")
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_id")
    private Long testId;

    @Column(name="title")
    private String testTitle;

    @Column(name = "description")
    private String description;

    @Column(name="link_expiry_date")
    private LocalDate linkExpiryDate;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Section> sections;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Interpretation> interpretations;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations;
}
