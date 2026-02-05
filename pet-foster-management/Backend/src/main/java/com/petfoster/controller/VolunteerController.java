package com.petfoster.controller;

import com.petfoster.dto.volunteer.*;
import com.petfoster.service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    // ADMIN
    @PostMapping("/events")
    public ResponseEntity<String> createEvent(
            @Valid @RequestBody EventCreateDTO dto,
            Authentication auth) {

        volunteerService.createEvent(dto, auth.getName());
        return ResponseEntity.ok("Event created");
    }

    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(volunteerService.getAllEvents());
    }

    @GetMapping("/events/{eventId}/registrations")
    public ResponseEntity<?> getEventRegistrations(@PathVariable Long eventId) {
        return ResponseEntity.ok(volunteerService.getRegistrationsByEvent(eventId));
    }

    // CUSTOMER
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody VolunteerRegisterDTO dto,
            Authentication auth) {

        volunteerService.registerVolunteer(dto, auth.getName());
        return ResponseEntity.ok("Registered successfully");
    }

    @GetMapping("/my")
    public ResponseEntity<?> myRegs(Authentication auth) {
        return ResponseEntity.ok(
                volunteerService.myRegistrations(auth.getName()));
    }
}
