package com.project.hospitalManagement_ProductionThings;

import com.project.hospitalManagement_ProductionThings.model.Appointment;
import com.project.hospitalManagement_ProductionThings.model.Insurance;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import com.project.hospitalManagement_ProductionThings.service.AppointmentService;
import com.project.hospitalManagement_ProductionThings.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {


//    @Autowired
//    private InsuranceService insuranceService;
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @Test
//    public void testInsurance() {
//        Insurance  insurance = Insurance.builder()
//                .policyNumber("SBI_4321")
//                .provider("SBI")
//                .validUntil(LocalDate.of(2031, 12, 12))
//                .build();
//
//        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 2L);
//        System.out.println(patient);
//
//
//        var newPatient = insuranceService.disassociateInsuraceFromPatient(patient.getId());
//        System.out.println(newPatient);
//    }
//
//
//    @Test
//    public void testAppointment() {
//        Appointment appointment = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2026, 02, 10, 14, 0))
//                .reason("Fever")
//                .build();
//
//        var newAppointment =  appointmentService.createNewAppointment(appointment, 1L, 2L);
//        System.out.println(newAppointment);
//
//
//        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
//        System.out.println(updatedAppointment);
//    }
}
