package com.project.hospitalManagement_ProductionThings.controller;

import com.project.hospitalManagement_ProductionThings.dto.LoginRequestDto;
import com.project.hospitalManagement_ProductionThings.dto.LoginResponseDto;
import com.project.hospitalManagement_ProductionThings.dto.SignupResponseDto;
import com.project.hospitalManagement_ProductionThings.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


    @PostMapping("/signup")     // We are not creating SIgnUpRequestDto bcoz we want username and passwor only that why using same loginRequestDto
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto signupResponseDto) {
        return ResponseEntity.ok(authService.signup(signupResponseDto));
    }
}
