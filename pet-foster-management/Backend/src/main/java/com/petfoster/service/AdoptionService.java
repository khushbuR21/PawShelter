package com.petfoster.service;

import com.petfoster.dto.adoption.AdoptionRequestDTO;
import com.petfoster.dto.adoption.AdoptionStatusDTO;
import com.petfoster.entity.AdoptionRequest;

import java.util.List;

public interface AdoptionService {

    AdoptionRequest createRequest(AdoptionRequestDTO dto, String customerEmail);

    AdoptionRequest updateStatus(Long requestId, AdoptionStatusDTO dto);

    List<AdoptionRequest> getAllRequests();

    List<AdoptionRequest> getMyRequests(String customerEmail);
}
