package com.example.auth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <register_request>
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
