package com.project.hospitalManagement_ProductionThings.repository;

import com.project.hospitalManagement_ProductionThings.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}