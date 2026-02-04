package com.project.hospitalManagement_ProductionThings.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private Long id;

    private LocalDateTime appointmentDateTime;

    private String reason;

    private DoctorReponseDto doctor;
}
