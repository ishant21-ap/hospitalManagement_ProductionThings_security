package com.project.hospitalManagement_ProductionThings.service;

import com.project.hospitalManagement_ProductionThings.dto.AppointmentResponseDto;
import com.project.hospitalManagement_ProductionThings.dto.CreateAppointmentRequestDto;
import com.project.hospitalManagement_ProductionThings.model.Appointment;
import com.project.hospitalManagement_ProductionThings.model.Doctor;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import com.project.hospitalManagement_ProductionThings.repository.AppointmentRepository;
import com.project.hospitalManagement_ProductionThings.repository.DoctorRepository;
import com.project.hospitalManagement_ProductionThings.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final ModelMapper modelMapper;



    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {

        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID : " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID : " + doctorId));

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentDateTime())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);     // to maintain consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }



    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);   // this will automatically called the update, becuase it is dirty now

        doctor.getAppointments().add(appointment);   // just for bidirectional consistency

        return appointment;
    }



    public List<AppointmentResponseDto> getAllAppointmentOfDoctor(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        return doctor.getAppointments().stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
