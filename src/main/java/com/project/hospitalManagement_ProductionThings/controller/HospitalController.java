package com.project.hospitalManagement_ProductionThings.controller;

import com.project.hospitalManagement_ProductionThings.dto.DoctorReponseDto;
import com.project.hospitalManagement_ProductionThings.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class HospitalController {

    private  final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorReponseDto>> getAllDoctors() {
        return  ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
