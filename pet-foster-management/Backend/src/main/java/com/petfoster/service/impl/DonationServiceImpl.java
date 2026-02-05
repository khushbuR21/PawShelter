package com.petfoster.service.impl;

import com.petfoster.dto.donation.*;
import com.petfoster.entity.*;
import com.petfoster.repository.*;
import com.petfoster.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DonationResponseDTO donatePet(
            DonationCreateDTO dto, String donorEmail) {

        User donor = userRepository.findByEmail(donorEmail)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        Pet pet = Pet.builder()
                .name(dto.getName())
                .gender(dto.getGender())
                .description(dto.getDescription())
                .type(dto.getType())
                .breed(dto.getBreed())
                .color(dto.getColor())
                .age(dto.getAge())
                .createdBy(donor)
                .createdAt(LocalDateTime.now())
                .build();

        Pet savedPet = petRepository.save(pet);

        Donation donation = Donation.builder()
                .pet(savedPet)
                .donor(donor)
                .donatedAt(LocalDateTime.now())
                .build();

        Donation savedDonation = donationRepository.save(donation);

        return mapToResponse(savedDonation);
    }

    @Override
    public List<DonationResponseDTO> getMyDonations(String donorEmail) {

        User donor = userRepository.findByEmail(donorEmail)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        return donationRepository.findByDonorId(donor.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonationResponseDTO> getAllDonations() {

        return donationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private DonationResponseDTO mapToResponse(Donation donation) {

        return DonationResponseDTO.builder()
                .donationId(donation.getId())
                .petId(donation.getPet().getId())
                .petName(donation.getPet().getName())
                .donorEmail(donation.getDonor().getEmail())
                .donatedAt(donation.getDonatedAt())
                .build();
    }
}
