package com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "use_test")
public class UserTest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

}
