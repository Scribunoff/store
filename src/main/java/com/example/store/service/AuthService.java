package com.example.store.service;

import com.example.store.payload.request.LoginRequest;
import com.example.store.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest loginRequest);
    JwtResponse refresh(String token);
}
