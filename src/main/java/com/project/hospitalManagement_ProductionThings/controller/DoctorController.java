package com.project.hospitalManagement_ProductionThings.controller;

import com.project.hospitalManagement_ProductionThings.dto.AppointmentResponseDto;
import com.project.hospitalManagement_ProductionThings.model.Appointment;
import com.project.hospitalManagement_ProductionThings.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {


    private final AppointmentService appointmentService;


    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor() {
        return ResponseEntity.ok(appointmentService.getAllAppointmentOfDoctor(1L));
    }
}
