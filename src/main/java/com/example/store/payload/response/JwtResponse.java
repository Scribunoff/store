package com.example.store.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
    private List<String> roles;

}
