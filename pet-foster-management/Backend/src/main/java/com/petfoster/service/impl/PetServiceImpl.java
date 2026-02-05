package com.petfoster.service.impl;

import com.petfoster.dto.pet.*;
import com.petfoster.entity.*;
import com.petfoster.repository.*;
import com.petfoster.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetImageRepository petImageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PetResponseDTO createPet(PetCreateDTO dto, String adminEmail) {

        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Pet pet = Pet.builder()
                .name(dto.getName())
                .gender(dto.getGender())
                .description(dto.getDescription())
                .type(dto.getType())
                .breed(dto.getBreed())
                .color(dto.getColor())
                .age(dto.getAge())
                .createdBy(admin)
                .createdAt(LocalDateTime.now())
                .build();

        Pet saved = petRepository.save(pet);

        return mapToResponse(saved);
    }

    @Override
    public List<PetResponseDTO> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PetResponseDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return mapToResponse(pet);
    }

    @Override
    public PetResponseDTO addImage(Long petId, String imageUrl) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        PetImage img = PetImage.builder()
                .pet(pet)
                .imageUrl(imageUrl)
                .isPrimary(pet.getImages() == null || pet.getImages().isEmpty())
                .uploadedAt(LocalDateTime.now())
                .build();
        petImageRepository.save(img);
        Pet updated = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        return mapToResponse(updated);
    }

    private PetResponseDTO mapToResponse(Pet pet) {
        return PetResponseDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .gender(pet.getGender())
                .description(pet.getDescription())
                .type(pet.getType())
                .breed(pet.getBreed())
                .color(pet.getColor())
                .age(pet.getAge())
                .createdAt(pet.getCreatedAt())
                .images(
                        pet.getImages() == null ? List.of() :
                        pet.getImages()
                                .stream()
                                .map(PetImage::getImageUrl)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
