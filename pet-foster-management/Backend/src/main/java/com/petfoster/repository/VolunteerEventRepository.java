package com.petfoster.repository;

import com.petfoster.entity.VolunteerEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerEventRepository extends JpaRepository<VolunteerEvent, Long> {
}
