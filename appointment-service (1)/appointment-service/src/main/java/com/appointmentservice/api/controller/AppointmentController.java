package com.appointmentservice.api.controller;

import com.appointmentservice.api.entity.Appointment;
import com.appointmentservice.api.payload.Doctor;
import com.appointmentservice.api.payload.Patient;
import com.appointmentservice.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
    @RequestMapping("/appointments")
    public class AppointmentController {
    private final RestTemplate restTemplate;
        private final AppointmentService appointmentService;

        @Autowired
        public AppointmentController(RestTemplate restTemplate, AppointmentService appointmentService) {
            this.restTemplate = restTemplate;
            this.appointmentService = appointmentService;
        }

        @GetMapping
        public ResponseEntity<List<Appointment>> getAllAppointments() {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
            ResponseEntity<Patient> patientResponse = restTemplate.getForEntity(
                    "http://patient-service/patients/"+appointment.getPatientId(),
                    Patient.class);
            if (patientResponse.getStatusCode() != HttpStatus.OK || patientResponse.getBody()== null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Patient patient=patientResponse.getBody();
            ResponseEntity<Doctor> DoctorResponse = restTemplate.getForEntity(
                    "http://doctor-service/doctors/"+appointment.getDoctorId(),
                    Doctor.class);

            if (DoctorResponse.getStatusCode() != HttpStatus.OK || DoctorResponse.getBody()== null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Doctor doctor = DoctorResponse.getBody();

             Appointment newAppointment = appointmentService.saveAppointment(appointment);


            Appointment createdAppointment = appointmentService.createAppointment(newAppointment);
            return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

