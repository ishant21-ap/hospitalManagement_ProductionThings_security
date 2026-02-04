package com.project.hospitalManagement_ProductionThings.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)  // owninig side thats why @JOin bcoz appointment bina pateint ke nahi reh skti
    private Patient patient;    // owning side


    // Appointment bina docotr ke nahi  reh  skti, jabki doctor bina appointment ke reh skta hai issliye this owns relationship
    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
