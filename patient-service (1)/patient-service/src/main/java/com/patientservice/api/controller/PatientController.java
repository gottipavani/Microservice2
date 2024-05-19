package com.patientservice.api.controller;

import com.patientservice.api.entity.Patient;
import com.patientservice.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/patients")
    public class PatientController {
        private final PatientService patientService;

        @Autowired
        public PatientController(PatientService patientService) {
            this.patientService = patientService;
        }

        @GetMapping
        public ResponseEntity<List<Patient>> getAllPatients() {
            List<Patient> patients = patientService.getAllPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
      //  http://localhost:8080/patients/1
        @GetMapping("/{id}")
        public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
            Patient patient = patientService.getPatientById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
            Patient createdPatient = patientService.createPatient(patient);
            return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
            Patient updatedPatient = patientService.updatePatient(id, patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

