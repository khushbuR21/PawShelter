package com.petfoster.service;

import com.petfoster.dto.pet.PetCreateDTO;
import com.petfoster.dto.pet.PetResponseDTO;

import java.util.List;

public interface PetService {

    PetResponseDTO createPet(PetCreateDTO dto, String adminEmail);

    List<PetResponseDTO> getAllPets();

    PetResponseDTO getPetById(Long id);

    PetResponseDTO addImage(Long petId, String imageUrl);
}
