package com.project.hospitalManagement_ProductionThings.service;

import com.project.hospitalManagement_ProductionThings.dto.PatientResponseDto;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import com.project.hospitalManagement_ProductionThings.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with id " + id));
        return modelMapper.map(patient, PatientResponseDto.class);
    }


    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream().map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
