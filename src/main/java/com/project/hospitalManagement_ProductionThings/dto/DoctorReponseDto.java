package com.project.hospitalManagement_ProductionThings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReponseDto {

    private Long id;
    private String name;
    private String specilization;
    private String email;
}
