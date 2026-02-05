package com.petfoster.dto.pet;

import com.petfoster.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PetResponseDTO {

    private Long id;
    private String name;
    private Gender gender;
    private String description;
    private String type;
    private String breed;
    private String color;
    private int age;
    private LocalDateTime createdAt;
    private List<String> images;
}
