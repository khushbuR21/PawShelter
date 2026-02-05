package com.petfoster.entity;

import com.petfoster.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String description;

    private String type;

    private String breed;

    private String color;

    private int age;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetImage> images;
}
