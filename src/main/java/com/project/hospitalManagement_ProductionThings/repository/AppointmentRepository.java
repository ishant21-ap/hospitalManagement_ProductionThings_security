package com.project.hospitalManagement_ProductionThings.repository;

import com.project.hospitalManagement_ProductionThings.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}