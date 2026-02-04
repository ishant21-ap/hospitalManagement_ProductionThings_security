package com.project.hospitalManagement_ProductionThings.model;

import jakarta.persistence.*;
import lombok.*;

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
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;


    // Here department owns even without creating @Join, so it is not necessary to use @Join in order to create column
    @OneToOne
    private Doctor headDoctor;

    // Now one department has many doctors
    // Many department can have many doctors
    // Now will using @ManyToMany we have to  use @JOinTable which is third table, without using @JoinTaable, third table is getting created be default only when using @ManyTomany
    @ManyToMany
    @JoinTable(
            name = "my_dpt_doctor",
            joinColumns = @JoinColumn(name = "dept_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )   // we can also define it
    private Set<Doctor> doctors = new HashSet<>();   // importatnt to intilize it with new HashSet<>(), so that when hibernate try to insert into set it not get null by default
}
