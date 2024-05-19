package com.doctorservice.api.controller;


import com.doctorservice.api.entity.Doctor;
import com.doctorservice.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/doctors")
    public class DoctorController {
        private final DoctorService doctorService;

        @Autowired
        public DoctorController(DoctorService doctorService) {
            this.doctorService = doctorService;
        }

        @GetMapping
        public ResponseEntity<List<Doctor>> getAllDoctors() {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
            Doctor doctor = doctorService.getDoctorById(id);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
            Doctor createdDoctor = doctorService.createDoctor(doctor);
            return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
            Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
            doctorService.deleteDoctor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

