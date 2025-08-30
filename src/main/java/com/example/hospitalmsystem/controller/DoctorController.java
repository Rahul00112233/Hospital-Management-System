package com.example.hospitalmsystem.controller;


import com.example.hospitalmsystem.model.Doctor;
import com.example.hospitalmsystem.service.DoctorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorServices doctorServices;

    public DoctorController(DoctorServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor, @RequestParam Long departmentId){
        return new ResponseEntity<>(doctorServices.createDoctor(doctor,departmentId), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return new ResponseEntity<>(doctorServices.findAllDoctor(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id){
        return new ResponseEntity<>(doctorServices.findDoctorById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, @PathVariable Long id, @RequestParam(required = false) Long departmentId){
        return new ResponseEntity<>(doctorServices.updateDoctor(doctor,id,departmentId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorServices.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
