package com.project.hospitalManagement_ProductionThings.model;


import com.project.hospitalManagement_ProductionThings.enums.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Setter
@Table(
        uniqueConstraints = {      // This is how we make Unique contraint when we need to use more column at a time
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

  //  @ToString.Exclude    // if you dont want to print specific field while using toString you can use this
    private LocalDate birthDate;

    @Column(unique = true)    // when we have single column then we make it unique like this
    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;



//    Type	Meaning
//    PERSIST	Save child when parent saved
//    MERGE 	Update child when parent updated
//    REMOVE	Delete child when parent deleted
//    REFRESH	Reload child from DB
//    DETACH	Remove child from context
//    ALL	    All above
//Cascade PERSIST saves the related entity automatically, and MERGE updates it
//    automatically when the parent entity is saved or merged.”
//    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")   // this is how we can set name, be defaault it will tableName_id
    private Insurance insurance;    // owning side
//Now this will be unidirectional, means we can retrive insurance using patient table, but we cant retirive patient
    // with insurance table, to make it like that we have to make it bidirectiona, and for that we have to add
    // field in insurance table also, also we have to use mappedBy inorder to dont make a new column in insurance table as well



    // Difference between  orphanRemoval = ture and CascadeType.Remove, using orphan true we can only delete child
    // means automatically chid will get deleted if it is not referencce to any object, parent will not get deleted,
    // in cascade.remove parent as well as child will get deleted.



    // Now this is inverse side still we are using cascading here because, if we delete patients then its all  appointment
    // should also get deleted, but if we delete appointment,  we dont need to delete patient
    // by using orphanRemoval = true, we are simply deleting appointment not patient
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true)    // inverse side
  @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

    //@Column(updatable = false)   // Yeh column kabhi update nahi hona chaiye future mein bhi kabhi nahi
}
