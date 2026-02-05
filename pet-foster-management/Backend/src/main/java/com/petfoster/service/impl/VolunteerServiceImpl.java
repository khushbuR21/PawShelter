package com.petfoster.service.impl;

import com.petfoster.dto.volunteer.*;
import com.petfoster.entity.*;
import com.petfoster.repository.*;
import com.petfoster.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerEventRepository eventRepo;

    @Autowired
    private VolunteerRegistrationRepository regRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void createEvent(EventCreateDTO dto, String adminEmail) {

        User admin = userRepo.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        VolunteerEvent event = VolunteerEvent.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                .eventTime(dto.getEventTime())
                .venue(dto.getVenue())
                .createdBy(admin)
                .createdAt(LocalDateTime.now())
                .build();

        eventRepo.save(event);
    }

    @Override
    public List<EventResponseDTO> getAllEvents() {
        return eventRepo.findAll().stream()
                .map(e -> EventResponseDTO.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .description(e.getDescription())
                        .eventDate(e.getEventDate())
                        .eventTime(e.getEventTime())
                        .venue(e.getVenue())
                        .createdAt(e.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<RegistrationDetailDTO> getRegistrationsByEvent(Long eventId) {
        return regRepo.findByEventId(eventId).stream()
                .map(reg -> RegistrationDetailDTO.builder()
                        .registrationId(reg.getId())
                        .customerName(reg.getCustomer().getName())
                        .customerEmail(reg.getCustomer().getEmail())
                        .registeredAt(reg.getRegisteredAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void registerVolunteer(
            VolunteerRegisterDTO dto, String customerEmail) {

        User customer = userRepo.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        VolunteerEvent event = eventRepo.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        VolunteerRegistration reg = VolunteerRegistration.builder()
                .customer(customer)
                .event(event)
                .registeredAt(LocalDateTime.now())
                .build();

        regRepo.save(reg);
    }

    @Override
    public List<MyRegistrationResponseDTO> myRegistrations(String customerEmail) {

        User customer = userRepo.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return regRepo.findByCustomerId(customer.getId()).stream()
                .map(reg -> {
                    VolunteerEvent e = reg.getEvent();
                    return MyRegistrationResponseDTO.builder()
                            .registrationId(reg.getId())
                            .eventId(e.getId())
                            .eventTitle(e.getTitle())
                            .eventDescription(e.getDescription())
                            .eventDate(e.getEventDate())
                            .eventTime(e.getEventTime())
                            .venue(e.getVenue())
                            .registeredAt(reg.getRegisteredAt())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
