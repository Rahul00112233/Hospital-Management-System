package com.example.hospitalmsystem.repository;

import com.example.hospitalmsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.status <> 'Cancelled' " +
            "AND :startTime < a.endTime " +
            "AND :endTime > a.startTime")
    List<Appointment> findOverlapping(
            @Param("doctorId") Long doctorId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctor_IdAndStartTimeBetween(Long doctorId, LocalDateTime from, LocalDateTime to);
}
