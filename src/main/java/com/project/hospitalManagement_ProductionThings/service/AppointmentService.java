package com.project.hospitalManagement_ProductionThings.service;

import com.project.hospitalManagement_ProductionThings.model.Appointment;
import com.project.hospitalManagement_ProductionThings.model.Doctor;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import com.project.hospitalManagement_ProductionThings.repository.AppointmentRepository;
import com.project.hospitalManagement_ProductionThings.repository.DoctorRepository;
import com.project.hospitalManagement_ProductionThings.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null){
            throw new IllegalArgumentException("Appointment already exists");
        }

        // now appointment owns the relationship, that why
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);   // to maintain birectional consistency

        return appointmentRepository.save(appointment);
    }



    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);   // this will automatically called the update, becuase it is dirty now

        doctor.getAppointments().add(appointment);   // just for bidirectional consistency

        return appointment;
    }
}
