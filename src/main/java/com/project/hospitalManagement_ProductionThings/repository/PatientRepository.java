package com.project.hospitalManagement_ProductionThings.repository;

import com.project.hospitalManagement_ProductionThings.dto.BloodGroupCountResponseEntity;
import com.project.hospitalManagement_ProductionThings.enums.BloodGroupType;
import com.project.hospitalManagement_ProductionThings.model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findByNameContainingOrderByIdDesc(String query);


    // This is JPQL query not a native query, here we depends on JPA (Entity)
    @Query("select p from Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);


    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);


    // here response of below query is dynamic, like there will be two columns thats why we have Object[]
//    @Query("select p.bloodGroup, Count(p) from Patient p group by p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();
    @Query("select new com.project.hospitalManagement_ProductionThings.dto.BloodGroupCountResponseEntity(p.bloodGroup, Count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();


    @Modifying     // It tells that this query is suppose to update the database
    @Transactional
    @Query("update Patient p set p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);



    // Native query
    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);
//    List<Patient> findAllPatients();



}
