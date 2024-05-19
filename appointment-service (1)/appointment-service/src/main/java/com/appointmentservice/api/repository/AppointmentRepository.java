package com.appointmentservice.api.repository;

import com.appointmentservice.api.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        // Additional custom query methods can be defined here if needed
    }

