package com.dailypulse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for handling authentication requests.
 * Used to receive username and password from the client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String jwt;
}
