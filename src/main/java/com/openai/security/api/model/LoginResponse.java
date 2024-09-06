package com.openai.security.api.model;

public record LoginResponse(String accessToken, String refreshToken, Long expiresIn) {
}