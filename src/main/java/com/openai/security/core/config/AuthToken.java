package com.openai.security.core.config;

import com.openai.common.api.domain.model.Role;
import com.openai.common.api.domain.model.User;
import com.openai.security.domain.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class AuthToken {

    private final JwtEncoder jwtEncoder;
    private  final LoginService loginService;
    private final JwtUtils jwtUtils;

    private static final Integer MIN_REFRESH_TOKEN = 2100;

    @Value("${jwt.expires.in}")
    private Duration expiresIn;

    public AuthToken(JwtEncoder jwtEncoder, LoginService loginService, JwtUtils jwtUtils) {
        this.jwtEncoder = jwtEncoder;
        this.loginService = loginService;

        this.jwtUtils = jwtUtils;
    }

    public Token getAuthUser(String email, String password) {
        var user = loginService.login(email, password);
        var token = authenticateUser(user);
        return token;
    }

    public Token authenticateUser(User user) {
        var now = Instant.now();

        var token = getToken(user, now.plus(expiresIn));
        var refreshToken = getToken(user, now.plus(expiresIn.plusMinutes(MIN_REFRESH_TOKEN)));

        return new Token(token, refreshToken, expiresIn.getSeconds());
    }

    public Token refreshToken(String refreshToken) {
        var userId = jwtUtils.getUserIdFromToken(refreshToken);
        var user = loginService.findUserId(Long.valueOf(userId));
        var token = authenticateUser(user);

        return token;
    }

    private String getToken(User user, Instant expiresIn) {
        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("parallax-api")
                .subject(user.getId().toString())
                .issuedAt(Instant.now())
                .expiresAt(expiresIn)
                .claim("scope", scopes)
                .claim("status", user.isActive())
                .claim("user_id", user.getId().toString())
                .claim("user_name", user.getName())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
