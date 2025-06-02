package com.self_discovery.self_discovery.selfdiscovery.controller;

import com.self_discovery.self_discovery.selfdiscovery.domain.dtos.RegistrationRequestDTO;
import com.self_discovery.self_discovery.selfdiscovery.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/add")
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDTO request) {
        String result = registrationService.registerUserOrAdmin(request);
        return ResponseEntity.ok(result);
    }
}
