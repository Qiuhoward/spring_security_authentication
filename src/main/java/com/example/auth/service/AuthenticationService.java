package com.example.auth.service;

import com.example.auth.dto.AuthenticationRequest;
import com.example.auth.dto.AuthenticationResponse;
import com.example.auth.dto.RegisterRequest;
import com.example.auth.entity.Role;
import com.example.auth.entity.UserDetail;
import com.example.auth.entity.UserRepository;
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
       var user= UserDetail.builder()
               .firstname(request.getFirstName())
               .lastname(request.getLastName())
               .role(Role.USER)
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword1()))
               .build();
        System.out.println(user);
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
        //做帳號密碼認證
        System.out.println(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),request.getPassword()));
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
