package com.petfoster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;

    private LocalDateTime donatedAt;
}
