package com.example.hospitalmsystem.service;


import com.example.hospitalmsystem.model.Department;
import com.example.hospitalmsystem.model.Doctor;
import com.example.hospitalmsystem.repository.DepartmentRepo;
import com.example.hospitalmsystem.repository.DoctorRepo;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServices {

    private final DoctorRepo doctorRepo;
    private final DepartmentRepo departmentRepo;

    public class NotFoundException extends RuntimeException {
        public NotFoundException(String message) { super(message); }
    }

    public DoctorServices(DoctorRepo doctorRepo, DepartmentRepo departmentRepo) {
        this.doctorRepo = doctorRepo;
        this.departmentRepo = departmentRepo;
    }

    public Doctor createDoctor(Doctor doctor,Long departmentId){
        Department dep = departmentRepo.findById(departmentId)
                .orElseThrow(()-> new NotFoundException("department id not found"));
        doctor.setDepartment(dep);
        return doctorRepo.save(doctor);
    }

    public List<Doctor> findAllDoctor(){
        return doctorRepo.findAll();
    }

    public Doctor findDoctorById(Long doctorId){
        return doctorRepo.findById(doctorId)
                .orElseThrow(()-> new NotFoundException("doctor id not found"));
    }

    public Doctor updateDoctor(Doctor newDoctor,Long doctorId, Long departmentId){
        Doctor d = findDoctorById(doctorId);
        d.setName(newDoctor.getName());
        d.setEmail(newDoctor.getEmail());
        d.setSpecialization(newDoctor.getSpecialization());
        if(departmentId!=null){
            Department dep = departmentRepo.findById(departmentId)
                    .orElseThrow(()-> new NotFoundException("department id not found"));
            d.setDepartment(dep);
        }
        return doctorRepo.save(d);
    }

    public void deleteDoctor(Long doctorId){
        doctorRepo.deleteById(doctorId);
    }
}
