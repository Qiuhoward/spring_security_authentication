package com.example.authentication.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * <認證請求參數>
 */
@Getter
@Setter
public class AuthenticationRequest {
    private String email;
    private String password;
}
