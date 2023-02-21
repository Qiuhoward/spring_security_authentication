package com.example.auth.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * <auth_request>
 */
@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
}
