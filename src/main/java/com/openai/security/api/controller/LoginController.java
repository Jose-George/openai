package com.openai.security.api.controller;

import com.openai.security.api.model.LoginRequest;
import com.openai.security.api.model.LoginResponse;
import com.openai.security.api.model.RefreshTokenRequest;
import com.openai.security.core.config.AuthToken;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthToken authToken;

    public LoginController(AuthToken authToken) {
        this.authToken = authToken;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        var token = authToken.getAuthUser(loginRequest.email(), loginRequest.password());
        return ResponseEntity.ok(new LoginResponse(token.accessToken(), token.refreshToken(), token.expiresIn()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        var token = authToken.refreshToken(refreshTokenRequest.token());
        return ResponseEntity.ok(new LoginResponse(token.accessToken(), token.refreshToken(), token.expiresIn()));
    }

}