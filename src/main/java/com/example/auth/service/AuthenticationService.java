package com.example.auth.service;

import com.example.auth.dto.AuthenticationRequest;
import com.example.auth.dto.AuthenticationResponse;
import com.example.auth.dto.RegisterRequest;
import com.example.auth.entity.Role;
import com.example.auth.entity.user.UserDetail;
import com.example.auth.entity.user.UserRepository;
import com.example.auth.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author howard
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;


    /**
     * 註冊
     */
    public ResponseEntity<Object> register(RegisterRequest request) {
        if (!Objects.equals(request.getPassword1(), request.getPassword2())) {
            return ResponseEntity.badRequest().body("密碼兩次不一致");
        } else if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("帳號已存在");
        }

        var user = UserDetail.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .role(Role.USER)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword1()))
                .build();

        userRepository.save(user);
        return ResponseEntity.ok(getResponseToken(user));
    }


    /**
     * 認證
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();
        return getResponseToken(user);
    }

    /**
     * 回傳token和存進redis設置過期時間
     * (先緩存 以備不時之需)
     */
    public AuthenticationResponse getResponseToken(UserDetail user) {
        var jsonToken = jwtService.generateToken(user);
        tokenUtils.storeToken(user.getUsername(), jsonToken);
        return AuthenticationResponse
                .builder()
                .token(jsonToken)
                .build();
    }


}
