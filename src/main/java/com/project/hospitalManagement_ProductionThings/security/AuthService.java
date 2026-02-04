package com.project.hospitalManagement_ProductionThings.security;

import com.project.hospitalManagement_ProductionThings.dto.LoginRequestDto;
import com.project.hospitalManagement_ProductionThings.dto.LoginResponseDto;
import com.project.hospitalManagement_ProductionThings.dto.SignupResponseDto;
import com.project.hospitalManagement_ProductionThings.model.User;
import com.project.hospitalManagement_ProductionThings.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;

    private final AuthUtil authUtil;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        LoginResponseDto loginResponseDto = modelMapper.map(user, LoginResponseDto.class);

        loginResponseDto.setJwt(token);

        return loginResponseDto;

    }

    public SignupResponseDto signup(LoginRequestDto signupResponseDto) {
    // check whether user already exists or not
        User user = userRepository.findByUsername(signupResponseDto.getUsername()).orElse(null);

        if (user != null) {
            throw new IllegalStateException("Username is already in use");
        }

        user = userRepository.save(User.builder()
                .username(signupResponseDto.getUsername())
                .password(passwordEncoder.encode(signupResponseDto.getPassword())).build());

        return modelMapper.map(user, SignupResponseDto.class);
    }
}
