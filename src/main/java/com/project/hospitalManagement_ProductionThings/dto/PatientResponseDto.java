package com.project.hospitalManagement_ProductionThings.dto;

import com.project.hospitalManagement_ProductionThings.enums.BloodGroupType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientResponseDto {

    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType  bloodGroupType;
}
