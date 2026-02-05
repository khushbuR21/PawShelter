package com.petfoster.controller;

import com.petfoster.dto.auth.*;
import com.petfoster.modelDTO.UserDTO;
import com.petfoster.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
    	System.out.println("/register called");
        UserDTO savedUser = authService.register(userDTO);
        return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(
            @Valid @RequestBody LoginDTO dto) {
    	System.out.println("/login called");
        return ResponseEntity.ok(authService.login(dto));
    }
}
