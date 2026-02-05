package com.petfoster.controller;

import com.petfoster.dto.pet.*;
import com.petfoster.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    private static final String UPLOAD_DIR = "uploads";

    // ADMIN
    @PostMapping
    public ResponseEntity<PetResponseDTO> createPet(
            @Valid @RequestBody PetCreateDTO dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                petService.createPet(dto, authentication.getName()));
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<PetResponseDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file,
            Authentication auth) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String ext = file.getOriginalFilename() != null && file.getOriginalFilename().contains(".")
                    ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))
                    : "";
            String filename = "pet-" + id + "-" + UUID.randomUUID().toString() + ext;
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);
            String imageUrl = "/uploads/" + filename;
            return ResponseEntity.ok(petService.addImage(id, imageUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // CUSTOMER + ADMIN
    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }
}
