package com.test.task.controller;

import com.test.task.dto.CustomerDTO;
import com.test.task.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Slf4j
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(authenticationService.createAuthenticationToken(customerDTO));
    }
}
