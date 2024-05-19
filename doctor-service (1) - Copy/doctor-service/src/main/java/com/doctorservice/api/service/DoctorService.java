package com.doctorservice.api.service;


import com.doctorservice.api.entity.Doctor;
import com.doctorservice.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class DoctorService {
        private final DoctorRepository doctorRepository;

        @Autowired
        public DoctorService(DoctorRepository doctorRepository) {
            this.doctorRepository = doctorRepository;
        }
        public Doctor saveDoctor(Doctor doctor)
        {
            return doctorRepository.save(doctor);
        }

        public List<Doctor> getAllDoctors() {
            return doctorRepository.findAll();
        }

        public Doctor getDoctorById(Long id) {
            return doctorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        }
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

 public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());

            return doctorRepository.save(doctor);
        }

        public void deleteDoctor(Long id) {
            doctorRepository.deleteById(id);
        }
    }


