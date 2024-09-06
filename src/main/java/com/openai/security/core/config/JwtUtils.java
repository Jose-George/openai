package com.openai.security.core.config;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtils {

    private final JwtDecoder jwtDecoder;

    public JwtUtils(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    public List<String> extractClaims(String jwtToken) {
        Jwt jwt = jwtDecoder.decode(jwtToken);
        Map<String, Object> claimsMap = jwt.getClaims();

        List<String> claimsList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : claimsMap.entrySet()) {
            claimsList.add(entry.getKey() + "=" + entry.getValue().toString());
        }

        return claimsList;
    }

    public String getUserIdFromToken(String jwtToken) {
        Jwt jwt = jwtDecoder.decode(jwtToken);
        Map<String, Object> claimsMap = jwt.getClaims();
        return claimsMap.get("user_id").toString();
    }

}
