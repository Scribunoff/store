package com.example.store.service;

import com.example.store.domain.user.User;
import com.example.store.payload.request.SignupRequest;

public interface UserService {
    User create(SignupRequest user);
    User getUserByUsername(String username);
    User getUserById(Long id);
}
