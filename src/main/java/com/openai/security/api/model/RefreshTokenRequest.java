package com.openai.security.api.model;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Token is mandatory.") String token) {
}