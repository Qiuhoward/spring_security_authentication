package com.example.authentication.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <註冊請求參數>
 */
@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password1;
    private String password2;
}
