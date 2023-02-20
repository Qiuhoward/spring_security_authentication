package com.example.authentication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <認證回應參數>
 */
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}
