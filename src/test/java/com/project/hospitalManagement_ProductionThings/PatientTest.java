package com.project.hospitalManagement_ProductionThings;

import com.project.hospitalManagement_ProductionThings.dto.BloodGroupCountResponseEntity;
import com.project.hospitalManagement_ProductionThings.enums.BloodGroupType;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import com.project.hospitalManagement_ProductionThings.repository.PatientRepository;
import com.project.hospitalManagement_ProductionThings.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.List;

@SpringBootTest    // necessary
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;


    @Test    // necessary, also return type should be void only
    public void testPatientRepository() {
//        List<Patient> patients = patientRepository.findAll();
//        System.out.println(patients);

//        Patient findByName = patientRepository.findByName("Diya");

//        List<Patient> patients = patientRepository.findByBloodGroup(bloodGroupType.A_POSITIVE);

//        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.of(1995, 3, 14));

//        List<Object[]> patients = patientRepository.countEachBloodGroupType();
//        for(Object[] objects : patients) {
//            System.out.println(objects[0] + " " + objects[1]);
//        }
//
//        List<BloodGroupCountResponseEntity> patients = patientRepository.countEachBloodGroupType();
//        for(BloodGroupCountResponseEntity bloodGroupCountResponseEntity : patients) {
//            System.out.println(bloodGroupCountResponseEntity);
//        }

//        List<Patient> patients = patientRepository.findAllPatients();

//        int patients = patientRepository.updateNameWithId("Alex Hellen", 1L);
//        System.out.println(patients);

//        for(Patient patient : patients) {
//            System.out.println(patient);
//        }


//        Page<Patient> patientPage =  patientRepository.findAll(PageRequest.of(0, 2));
        // we can also print data by sort on some sepecific fields
        Page<Patient> patientPage =  patientRepository.findAll(PageRequest.of(0, 2, Sort.by("name")));
        for(Patient patient : patientPage) {
            System.out.println(patient);
        }
    }


//    @Test
//    public void addPatient() {
//        Patient patient = Patient.builder()
//                .name("Virat Kohli")
//                .email("virat@gmail.com")
//                .birthDate(LocalDate.of(1986, 12, 11))
//                .gender("Male")
//                .build();
//        Patient saved = patientRepository.save(patient);
//
//        // assert is used to check whether
//        assertNotNull(saved.getId());
//        assertEquals("Virat Kohli", saved.getName());
//    }

//    @Test
//    public void getPatientById() {
//        Patient patient = patientService.getPatientById(2L);
//        System.out.println(patient);
//    }
}
