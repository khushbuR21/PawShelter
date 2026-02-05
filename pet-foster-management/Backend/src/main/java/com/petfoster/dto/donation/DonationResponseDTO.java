package com.petfoster.dto.donation;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DonationResponseDTO {

    private Long donationId;
    private Long petId;
    private String petName;
    private String donorEmail;
    private LocalDateTime donatedAt;
}
