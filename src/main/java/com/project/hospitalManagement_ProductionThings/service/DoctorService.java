package com.project.hospitalManagement_ProductionThings.service;

import com.project.hospitalManagement_ProductionThings.dto.DoctorReponseDto;
import com.project.hospitalManagement_ProductionThings.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final ModelMapper modelMapper;


    public List<DoctorReponseDto> getAllDoctors() {
        return doctorRepository.findAll().
                stream().map(doctor -> modelMapper.map(doctor, DoctorReponseDto.class))
                .collect(Collectors.toList());
    }
}
