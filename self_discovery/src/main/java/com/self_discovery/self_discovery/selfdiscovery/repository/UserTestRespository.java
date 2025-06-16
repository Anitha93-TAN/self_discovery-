package com.self_discovery.self_discovery.selfdiscovery.repository;

import com.self_discovery.self_discovery.selfdiscovery.self_discovery.entities.user.UserTest;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestRespository extends JpaRepository<UserTest,Long> {
}
