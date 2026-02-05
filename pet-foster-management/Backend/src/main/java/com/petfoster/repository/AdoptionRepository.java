package com.petfoster.repository;

import com.petfoster.entity.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRepository extends JpaRepository<AdoptionRequest, Long> {

    List<AdoptionRequest> findByCustomerId(Long customerId);

    List<AdoptionRequest> findByPetId(Long petId);
}
