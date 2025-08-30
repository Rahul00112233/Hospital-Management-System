package com.example.hospitalmsystem.service;


import com.example.hospitalmsystem.dto.AppointmentDTO;
import com.example.hospitalmsystem.model.Appointment;
import com.example.hospitalmsystem.model.Doctor;
import com.example.hospitalmsystem.model.Patient;
import com.example.hospitalmsystem.repository.AppointmentRepo;
import com.example.hospitalmsystem.repository.DoctorRepo;
import com.example.hospitalmsystem.repository.PatientRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServices {

    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;


    public AppointmentServices(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo, PatientRepo patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public class BadRequestException extends RuntimeException {
        public BadRequestException(String message) { super(message); }
    }

    public class NotFoundException extends RuntimeException {
        public NotFoundException(String message) { super(message); }
    }

    public Appointment createAppointment(AppointmentDTO appointmentRequest){
        if(appointmentRequest.getEndTime().isBefore(appointmentRequest.getStartTime())){
            throw new BadRequestException("End time cannot be before of Starting time");
        }

        Patient p = patientRepo.findById(appointmentRequest.getPatientId())
                .orElseThrow(()-> new NotFoundException("Patient Id not found"));

        Doctor d = doctorRepo.findById(appointmentRequest.getDoctorId())
                .orElseThrow(()-> new NotFoundException("Doctor Id not found"));

        List<Appointment> clashes = appointmentRepo.findOverlapping(
                appointmentRequest.getDoctorId(),
                appointmentRequest.getStartTime(),
                appointmentRequest.getEndTime()
        );
        if(!clashes.isEmpty()){
            throw new BadRequestException("Doctor is not free at this time");
        }
        Appointment a = new Appointment();
        a.setDoctor(d);
        a.setPatient(p);
        a.setStartTime(appointmentRequest.getStartTime());
        a.setEndTime(appointmentRequest.getEndTime());
        a.setStatus("Scheduled");
        return appointmentRepo.save(a);
    }

    public List<Appointment> getAllAppointment(){
        return appointmentRepo.findAll();
    }

    public Appointment findAppointment(Long id){
        return appointmentRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("Id not found"));
    }

    public List<Appointment> findDoctorInRange(Long doctorId, LocalDateTime from, LocalDateTime to){
        return appointmentRepo.findByDoctor_IdAndStartTimeBetween(doctorId,from, to);
    }

    public Appointment complete(Long id){
        Appointment a = findAppointment(id);
        a.setStatus("Completed");
        return appointmentRepo.save(a);
    }

    public Appointment cancelAppointment(Long id){
        Appointment a = findAppointment(id);
        a.setStatus("Cancelled");
        return appointmentRepo.save(a);
    }

    public void deleteAppointment(Long id){
        appointmentRepo.deleteById(id);
    }
}
