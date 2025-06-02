package com.self_discovery.self_discovery.selfdiscovery.controller;

import com.self_discovery.self_discovery.selfdiscovery.domain.dtos.RoleDTO;
import com.self_discovery.self_discovery.selfdiscovery.domain.entity.Role;
import com.self_discovery.self_discovery.selfdiscovery.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleDTO) {
        Role savedRole = roleService.createRole(roleDTO);
        return ResponseEntity.ok(savedRole);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.updateRole(id, roleDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
