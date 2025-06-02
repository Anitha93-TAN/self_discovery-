package com.self_discovery.self_discovery.selfdiscovery.public_folder.service;

import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.dtos.AdminDTO;
import com.self_discovery.self_discovery.selfdiscovery.public_folder.domain.dtos.UserDTO;

import java.util.List;

public interface AdminService {
    List<UserDTO> getAllUsers();
    List<AdminDTO> getAllAdmins();
    AdminDTO updateAdmin(Long adminId, AdminDTO updatedAdmin);
    UserDTO updateUser(Long userId, UserDTO updatedUser);
    UserDTO getUserById(Long id, String currentUsername); // for user self-access
}

