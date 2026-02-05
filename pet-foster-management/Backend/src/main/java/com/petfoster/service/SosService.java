package com.petfoster.service;

import com.petfoster.dto.sos.*;

import java.util.List;

public interface SosService {

    SosResponseDTO create(SosCreateDTO dto, String email);

    List<SosResponseDTO> getAll();
}
