package com.dailypulse.auth_service;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
