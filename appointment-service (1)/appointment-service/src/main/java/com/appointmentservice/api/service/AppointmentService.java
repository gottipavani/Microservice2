package com.appointmentservice.api.service;


import com.appointmentservice.api.entity.Appointment;
import com.appointmentservice.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class AppointmentService {
        private final AppointmentRepository appointmentRepository;

        @Autowired
        public AppointmentService(AppointmentRepository appointmentRepository) {
            this.appointmentRepository = appointmentRepository;
        }
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAllAppointments() {
            return appointmentRepository.findAll();
        }

        public Appointment getAppointmentById(Long id) {
            return appointmentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        }
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
        public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
            Appointment appointment = appointmentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

            appointment.setDoctorId(updatedAppointment.getDoctorId());
            appointment.setPatientId(updatedAppointment.getPatientId());
            appointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
            // ... update other appointment properties as needed

            return appointmentRepository.save(appointment);
        }

        public void deleteAppointment(Long id) {
            appointmentRepository.deleteById(id);
        }
}

