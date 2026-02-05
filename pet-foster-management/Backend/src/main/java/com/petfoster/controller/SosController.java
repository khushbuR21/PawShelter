package com.petfoster.controller;

import com.petfoster.dto.sos.*;
import com.petfoster.service.SosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sos")
public class SosController {

    @Autowired
    private SosService sosService;

    // CUSTOMER
    @PostMapping
    public ResponseEntity<SosResponseDTO> create(
            @Valid @RequestBody SosCreateDTO dto,
            Authentication auth) {

        return ResponseEntity.ok(
                sosService.create(dto, auth.getName()));
    }

    // ADMIN
    @GetMapping
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(sosService.getAll());
    }
}
