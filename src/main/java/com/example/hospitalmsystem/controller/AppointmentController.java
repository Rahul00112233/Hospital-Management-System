package com.example.hospitalmsystem.controller;


import com.example.hospitalmsystem.dto.AppointmentDTO;
import com.example.hospitalmsystem.model.Appointment;
import com.example.hospitalmsystem.service.AppointmentServices;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentServices service;

    public AppointmentController(AppointmentServices service) {
        this.service = service;
    }


    @PostMapping("/book")
    @PreAuthorize("hasAnyRole('RECEPTIONIST')")
    public ResponseEntity<Appointment> book(@Valid @RequestBody AppointmentDTO req) {
        return ResponseEntity.ok(service.createAppointment(req));
    }

    @GetMapping("/{id}")
    public Appointment one(@PathVariable Long id) { return service.findAppointment(id); }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasAnyRole('DOCTOR','RECEPTIONIST','ADMIN')")
    public List<Appointment> byDoctorInRange(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return service.findDoctorInRange(doctorId, from, to);
    }

    @PutMapping("/{id}/complete")
    public Appointment complete(@PathVariable Long id) { return service.complete(id); }

    @PutMapping("/{id}/cancel")
    public Appointment cancel(@PathVariable Long id) { return service.cancelAppointment(id); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
