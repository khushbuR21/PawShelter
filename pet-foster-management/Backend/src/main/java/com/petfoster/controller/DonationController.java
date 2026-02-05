package com.petfoster.controller;

import com.petfoster.dto.donation.*;
import com.petfoster.service.DonationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    // CUSTOMER
    @PostMapping
    public ResponseEntity<DonationResponseDTO> donate(
            @Valid @RequestBody DonationCreateDTO dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                donationService.donatePet(dto, authentication.getName()));
    }

    // CUSTOMER
    @GetMapping("/my")
    public ResponseEntity<List<DonationResponseDTO>> myDonations(
            Authentication authentication) {

        return ResponseEntity.ok(
                donationService.getMyDonations(authentication.getName()));
    }

    // ADMIN
    @GetMapping
    public ResponseEntity<List<DonationResponseDTO>> allDonations() {

        return ResponseEntity.ok(
                donationService.getAllDonations());
    }
}
