package com.example.store.service.impl;

import com.example.store.payload.request.LoginRequest;
import com.example.store.payload.response.JwtResponse;
import com.example.store.security.jwt.JwtUtils;
import com.example.store.security.service.UserDetailsImpl;
import com.example.store.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Transactional
    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        jwtResponse.setId(userDetails.getId());
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setAccessToken(jwtUtils.createAccessToken(authentication));
        jwtResponse.setRefreshToken(jwtUtils.createRefreshToken(authentication));
        jwtResponse.setRoles(roles);
        return jwtResponse;
    }

    @Transactional
    @Override
    public JwtResponse refresh(String token) {
        return jwtUtils.refreshUserToken(token);
    }
}
