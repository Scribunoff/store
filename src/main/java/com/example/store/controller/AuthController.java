package com.example.store.controller;

import com.example.store.domain.User;
import com.example.store.dto.validation.OnCreate;
import com.example.store.mapper.UserMapper;
import com.example.store.payload.request.LoginRequest;
import com.example.store.payload.request.SignupRequest;
import com.example.store.payload.response.JwtResponse;
import com.example.store.service.AuthService;
import com.example.store.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated(OnCreate.class) @RequestBody SignupRequest signupRequest) {
        User createdUser = userService.create(signupRequest);
        return ResponseEntity.ok(userMapper.toDto(createdUser));
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refresh) {
        return authService.refresh(refresh);
    }
}
