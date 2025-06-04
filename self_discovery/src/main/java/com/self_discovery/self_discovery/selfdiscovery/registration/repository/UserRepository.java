package com.self_discovery.self_discovery.selfdiscovery.registration.repository;

import com.self_discovery.self_discovery.selfdiscovery.registration.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
