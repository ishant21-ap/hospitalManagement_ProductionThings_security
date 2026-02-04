package com.project.hospitalManagement_ProductionThings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentRequestDto {

    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentDateTime;
    private String reason;
}
