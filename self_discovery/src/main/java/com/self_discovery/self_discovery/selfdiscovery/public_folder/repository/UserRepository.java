package com.self_discovery.self_discovery.selfdiscovery.public_folder.repository;

import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
