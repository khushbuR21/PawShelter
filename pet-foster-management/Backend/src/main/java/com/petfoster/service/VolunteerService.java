package com.petfoster.service;

import com.petfoster.dto.volunteer.*;

import java.util.List;

public interface VolunteerService {

    void createEvent(EventCreateDTO dto, String adminEmail);

    List<EventResponseDTO> getAllEvents();

    List<RegistrationDetailDTO> getRegistrationsByEvent(Long eventId);

    void registerVolunteer(VolunteerRegisterDTO dto, String customerEmail);

    List<MyRegistrationResponseDTO> myRegistrations(String customerEmail);
}
