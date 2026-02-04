package com.project.hospitalManagement_ProductionThings.dto;

import com.project.hospitalManagement_ProductionThings.enums.BloodGroupType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BloodGroupCountResponseEntity {

    private BloodGroupType bloodGroupType;

    private Long count;

}
