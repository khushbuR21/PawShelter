package com.petfoster.controller;

import com.petfoster.dto.adoption.*;
import com.petfoster.entity.AdoptionRequest;
import com.petfoster.service.AdoptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    // CUSTOMER → create request
    @PostMapping
    public ResponseEntity<AdoptionRequest> create(
            @Valid @RequestBody AdoptionRequestDTO dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                adoptionService.createRequest(dto, authentication.getName()));
    }

    // ADMIN → view all adoption requests
    @GetMapping
    public ResponseEntity<List<AdoptionRequest>> getAllRequests() {
        return ResponseEntity.ok(adoptionService.getAllRequests());
    }

    // ADMIN / PET OWNER → approve or reject
    @PutMapping("/{id}/status")
    public ResponseEntity<AdoptionRequest> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody AdoptionStatusDTO dto) {

        return ResponseEntity.ok(
                adoptionService.updateStatus(id, dto));
    }

    // CUSTOMER → view my requests
    @GetMapping("/my")
    public ResponseEntity<List<AdoptionRequest>> myRequests(
            Authentication authentication) {

        return ResponseEntity.ok(
                adoptionService.getMyRequests(authentication.getName()));
    }
}
