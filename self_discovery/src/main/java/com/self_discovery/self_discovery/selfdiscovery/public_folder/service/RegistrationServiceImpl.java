package com.self_discovery.self_discovery.selfdiscovery.public_folder.service;

import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.dtos.RegistrationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.entity.Admin;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.entity.User;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.entity.Role;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.repository.RoleRepository;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.repository.AdminRepository;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String registerUserOrAdmin(RegistrationRequestDTO request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid role ID: " + request.getRoleId()));

        String roleCode = role.getRoleCode().toLowerCase();

        switch (roleCode) {
            case "admin123":
                Admin admin = new Admin();
                admin.setUsername(request.getUsername());
                admin.setEmail(request.getEmail());
                admin.setPasswordHash(request.getPassword());
                admin.setPhoneNumber(String.valueOf(request.getPhoneNumber()));
                admin.setRole(role);
                adminRepository.save(admin);
                return "Admin registered successfully.";

            case "user123":
                User user = new User();
                user.setUsername(request.getUsername());
                user.setEmail(request.getEmail());
                user.setPasswordHash(request.getPassword());
                user.setPhoneNumber(String.valueOf(request.getPhoneNumber()));
                user.setRole(role);
                userRepository.save(user);
                return "User registered successfully.";

            default:
                throw new RuntimeException("Unsupported role code: " + roleCode);
        }
    }
}
