package com.petfoster.dto.pet;

import com.petfoster.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetCreateDTO {

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

    private String description;

    private String type;

    private String breed;

    private String color;

    private int age;
}
