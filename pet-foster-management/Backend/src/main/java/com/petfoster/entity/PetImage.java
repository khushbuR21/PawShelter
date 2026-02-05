package com.petfoster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pet_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private boolean isPrimary;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
