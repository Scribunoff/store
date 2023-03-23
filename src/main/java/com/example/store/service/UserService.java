package com.example.store.service;

import com.example.store.domain.User;
import com.example.store.payload.request.SignupRequest;

import java.util.Optional;

public interface UserService {
    User create(SignupRequest user);
    User getUserByUsername(String username);
    User getUserById(Long id);
}
