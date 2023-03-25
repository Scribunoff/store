package com.example.store.service.impl;

import com.example.store.domain.user.ERole;
import com.example.store.domain.user.User;
import com.example.store.exception.UserNotFoundException;
import com.example.store.payload.request.SignupRequest;
import com.example.store.repository.UserRepository;
import com.example.store.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    @Override
    public User create(SignupRequest signupRequest) {
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent() ||
            userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalStateException("User already exist");
        }
        if (!signupRequest.getPassword().equals(signupRequest.getConfirmationPassword())) {
            throw new IllegalStateException("Password and password confirmation must be equal.");
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRoleSet(Set.of(ERole.ROLE_USER));

        return userRepository.save(user);
    }
}
