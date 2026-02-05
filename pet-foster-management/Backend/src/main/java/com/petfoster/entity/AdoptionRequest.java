package com.petfoster.entity;

import com.petfoster.enums.AdoptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    private LocalDateTime requestedAt;

    private LocalDateTime actionAt;
}
