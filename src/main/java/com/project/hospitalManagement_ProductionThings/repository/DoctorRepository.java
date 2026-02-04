package com.project.hospitalManagement_ProductionThings.repository;

import com.project.hospitalManagement_ProductionThings.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}