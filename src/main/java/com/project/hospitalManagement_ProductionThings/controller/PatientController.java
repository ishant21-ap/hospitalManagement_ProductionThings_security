package com.project.hospitalManagement_ProductionThings.controller;

import com.project.hospitalManagement_ProductionThings.dto.AppointmentResponseDto;
import com.project.hospitalManagement_ProductionThings.dto.CreateAppointmentRequestDto;
import com.project.hospitalManagement_ProductionThings.dto.PatientResponseDto;
import com.project.hospitalManagement_ProductionThings.service.AppointmentService;
import com.project.hospitalManagement_ProductionThings.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    private final AppointmentService appointmentService;


    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(
            @RequestBody CreateAppointmentRequestDto createAppointmentRequestDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }


    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getPatientProfile(){
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}
