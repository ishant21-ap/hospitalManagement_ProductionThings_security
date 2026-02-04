package com.project.hospitalManagement_ProductionThings.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardDoctorRequestDto {

    private Long userId;
    private String specilization;
    private String name;
}
