package com.petfoster.service;

import com.petfoster.dto.donation.DonationCreateDTO;
import com.petfoster.dto.donation.DonationResponseDTO;

import java.util.List;

public interface DonationService {

    DonationResponseDTO donatePet(DonationCreateDTO dto, String donorEmail);

    List<DonationResponseDTO> getMyDonations(String donorEmail);

    List<DonationResponseDTO> getAllDonations();
}
