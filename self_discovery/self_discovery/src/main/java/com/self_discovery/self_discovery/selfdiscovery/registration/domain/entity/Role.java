package com.self_discovery.self_discovery.selfdiscovery.registration.domain.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.self_discovery.self_discovery.selfdiscovery.registration.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor //it used to create default constructor/constructor without parameter
@AllArgsConstructor//used to create the parameterised constructor
@Entity//spring mark this class as entity class and used to create the table in db by using the fields in the class.
@Table(name = "role")//it will create the table in the db
public class Role extends BaseEntity {

    @Id //make the field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//increment the primary key field.
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name", nullable = false)//to set the condition in the db column
    private String roleName;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    //mapping
    @OneToMany(mappedBy = "role")//one role many admin
    @JsonManagedReference
    private List<Admin> admins = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private List<User> users = new ArrayList<>();


}
