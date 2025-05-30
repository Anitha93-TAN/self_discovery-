package com.self_discovery.self_discovery.selfdiscovery.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false) // Converted to snake_case
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false) // Converted to snake_case
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false) // Converted to snake_case
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false) // Converted to snake_case
    private String updatedBy;
    

}