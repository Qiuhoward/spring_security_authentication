package com.example.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <auth_response>
 */
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}
