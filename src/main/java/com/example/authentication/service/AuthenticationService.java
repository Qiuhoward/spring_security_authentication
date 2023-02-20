package com.example.authentication.service;

import com.example.authentication.dto.AuthenticationRequest;
import com.example.authentication.dto.AuthenticationResponse;
import com.example.authentication.dto.RegisterRequest;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    /**
     * 註冊
     */
    public AuthenticationResponse register(RegisterRequest request) {
        if(!Objects.equals(request.getPassword1(), request.getPassword2())){
            return null;
        }
       var user=User.builder()
               .firstname(request.getFirstName())
               .lastname(request.getLastName())
               .role(Role.USER)
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword1()))
               .build();

        userRepository.save(user);

        var jsonToken=jwtService.generateToken(user); //傳入參數
        return AuthenticationResponse.builder()//回傳token (之後就不用一直new dto)直接builder
                .token(jsonToken)
                .build();
    }

    /**
     * 認證
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        //UsernamePasswordAuthenticationToken用來代表username及password，其實作了Authentication。

        var jwtToken=jwtService.generateToken(user); //val為final var非final
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
