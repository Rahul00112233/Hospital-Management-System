package com.example.hospitalmsystem.service;

import com.example.hospitalmsystem.model.Patient;
import com.example.hospitalmsystem.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServices {

    private final PatientRepo patientRepo;

    public PatientServices(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public List<Patient> getAllPatients(){
        return patientRepo.findAll();
    }

    public Patient createPatient(Patient patient){
        return patientRepo.save(patient);
    }

    public Optional<Patient> getPatientById(Long id){
        return patientRepo.findById(id);
    }

    public Patient updatePatientById(Patient updatedpatient, Long id){
        return patientRepo.findById(id)
                .map(patient -> {
                    patient.setName(updatedpatient.getName());
                    patient.setGender(updatedpatient.getGender());
                    patient.setDob(updatedpatient.getDob());
                    patient.setContact(updatedpatient.getContact());
                    patient.setEmail(updatedpatient.getEmail());
                    return patientRepo.save(patient);
                }).orElseThrow(()-> new RuntimeException("Patient not found"));
    }

    public void deletePatientById(Long id){
        patientRepo.deleteById(id);
    }
}
