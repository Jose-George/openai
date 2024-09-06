package com.openai.security.domain.service;

import com.openai.common.api.domain.exception.EntityNotFoundException;
import com.openai.common.api.domain.model.User;
import com.openai.common.api.domain.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) {

        var user = userRepository.findByEmailAndIsActiveTrue(email).orElseThrow(
                () -> new EntityNotFoundException("user not found")
        );

        if (!user.isLoginCorrect(password, passwordEncoder)) {
            throw new BadCredentialsException("user or password is invalid!");
        }

        return user;
    }

    public User findUserId(Long id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user not found")
        );

        return user;
    }

}
