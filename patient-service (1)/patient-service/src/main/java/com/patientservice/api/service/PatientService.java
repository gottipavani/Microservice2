package com.patientservice.api.service;


import com.patientservice.api.entity.Patient;
import com.patientservice.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class PatientService {
        private final PatientRepository patientRepository;

        @Autowired
        public PatientService(PatientRepository patientRepository) {
            this.patientRepository = patientRepository;
        }

        public List<Patient> getAllPatients() {
            return patientRepository.findAll();
        }

        public Patient getPatientById(Long id) {
            return patientRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        }

        public Patient createPatient(Patient patient) {
            return patientRepository.save(patient);
        }

        public Patient updatePatient(Long id, Patient updatedPatient) {
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

            patient.setName(updatedPatient.getName());
            patient.setAge(updatedPatient.getAge());
            patient.setAddress(updatedPatient.getAddress());

            return patientRepository.save(patient);
        }

        public void deletePatient(Long id) {
            patientRepository.deleteById(id);
        }
    }

