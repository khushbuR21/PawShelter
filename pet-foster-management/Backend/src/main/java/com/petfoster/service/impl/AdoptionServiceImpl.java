package com.petfoster.service.impl;

import com.petfoster.dto.adoption.*;
import com.petfoster.entity.*;
import com.petfoster.enums.AdoptionStatus;
import com.petfoster.repository.*;
import com.petfoster.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public AdoptionRequest createRequest(
            AdoptionRequestDTO dto, String customerEmail) {

        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        AdoptionRequest request = AdoptionRequest.builder()
                .pet(pet)
                .customer(customer)
                .status(AdoptionStatus.PENDING)
                .requestedAt(LocalDateTime.now())
                .build();

        return adoptionRepository.save(request);
    }

    @Override
    public AdoptionRequest updateStatus(
            Long requestId, AdoptionStatusDTO dto) {

        AdoptionRequest request = adoptionRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(dto.getStatus());
        request.setActionAt(LocalDateTime.now());

        return adoptionRepository.save(request);
    }

    @Override
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRepository.findAll();
    }

    @Override
    public List<AdoptionRequest> getMyRequests(String customerEmail) {

        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return adoptionRepository.findByCustomerId(customer.getId());
    }
}
