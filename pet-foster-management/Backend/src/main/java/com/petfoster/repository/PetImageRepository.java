package com.petfoster.repository;

import com.petfoster.entity.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetImageRepository extends JpaRepository<PetImage, Long> {
}
