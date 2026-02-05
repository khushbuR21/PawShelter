package com.petfoster.repository;

import com.petfoster.entity.VolunteerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRegistrationRepository
        extends JpaRepository<VolunteerRegistration, Long> {

    List<VolunteerRegistration> findByCustomerId(Long customerId);

    List<VolunteerRegistration> findByEventId(Long eventId);
}
