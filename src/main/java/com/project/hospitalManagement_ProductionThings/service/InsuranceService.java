package com.project.hospitalManagement_ProductionThings.service;

import com.project.hospitalManagement_ProductionThings.model.Insurance;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import com.project.hospitalManagement_ProductionThings.repository.InsuranceRepository;
import com.project.hospitalManagement_ProductionThings.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor     // This annotation is coming from lombok, using this we dont need to  use @Autowired,
// basically this is constructor injection
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


//    With @Transactional, Hibernate automatically saves changes to managed
//    entities without calling save(), using dirty checking. Without it,
//    changes stay only in memory unless save() is called.
    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id " + patientId));

        patient.setInsurance(insurance);    // owning side
        insurance.setPatient(patient);     // we write this because in order to maintain bidirectional consistency

        return patient;

    }


    @Transactional
    public Patient disassociateInsuraceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id " + patientId));

        patient.setInsurance(null);
        return patient;
    }
}
