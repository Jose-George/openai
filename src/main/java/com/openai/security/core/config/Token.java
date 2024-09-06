package com.openai.security.core.config;

public record Token(String accessToken, String refreshToken, Long expiresIn) {
}
