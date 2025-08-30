package com.example.hospitalmsystem.repository;

import com.example.hospitalmsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {

    List<Doctor> findBySpecialization(String specialization);
}
