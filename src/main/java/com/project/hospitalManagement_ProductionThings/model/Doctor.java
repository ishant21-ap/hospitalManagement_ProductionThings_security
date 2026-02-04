package com.project.hospitalManagement_ProductionThings.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String specilization;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();
}

