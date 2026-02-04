package com.project.hospitalManagement_ProductionThings.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    String jwt;
    Long userId;
}
