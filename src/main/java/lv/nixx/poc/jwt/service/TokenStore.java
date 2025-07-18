package lv.nixx.poc.jwt.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    private final Map<String, String> refreshTokens = new ConcurrentHashMap<>();

    public void storeToken(String username, String refreshToken) {
        refreshTokens.put(username, refreshToken);
    }

    public boolean isValidRefreshToken(String username, String token) {
        return token.equals(refreshTokens.get(username));
    }
}