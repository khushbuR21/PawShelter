package com.petfoster.service.impl;

import com.petfoster.dto.sos.*;
import com.petfoster.entity.SosReport;
import com.petfoster.entity.User;
import com.petfoster.repository.SosRepository;
import com.petfoster.repository.UserRepository;
import com.petfoster.service.SosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SosServiceImpl implements SosService {

    @Autowired
    private SosRepository sosRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public SosResponseDTO create(SosCreateDTO dto, String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SosReport report = SosReport.builder()
                .message(dto.getMessage())
                .location(dto.getLocation())
                .customer(user)
                .createdAt(LocalDateTime.now())
                .build();

        return map(sosRepo.save(report));
    }

    @Override
    public List<SosResponseDTO> getAll() {

        return sosRepo.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private SosResponseDTO map(SosReport r) {
        return SosResponseDTO.builder()
                .id(r.getId())
                .message(r.getMessage())
                .location(r.getLocation())
                .customerEmail(r.getCustomer().getEmail())
                .createdAt(r.getCreatedAt())
                .build();
    }
}
