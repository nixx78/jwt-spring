package lv.nixx.poc.jwt.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtAuthService {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final TokenStore tokenStore;

    public JwtAuthService(AuthenticationManager authenticationManager,
                          AppUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          TokenStore tokenStore
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.tokenStore = tokenStore;
    }

    public Map<String, String> authenticateAndGenerateTokenPairs(String username, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User userDetails = userDetailsService.loadUserByUsername(username);

        String refreshToken = jwtUtil.generateRefreshToken(username);

        tokenStore.storeToken(username, refreshToken);

        return Map.of(
                "accessToken", jwtUtil.generateToken(userDetails),
                "refreshToken", refreshToken
        );
    }

    public Map<String, String> refreshToken(String refreshToken) {
        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new IllegalStateException("Invalid or expired refresh token");
        }

        String username = jwtUtil.getBody(refreshToken).getSubject();
        if (!tokenStore.isValidRefreshToken(username, refreshToken)) {
            throw new IllegalStateException("Invalid or expired refresh token");
        }

        User userDetails = userDetailsService.loadUserByUsername(username);

        String newRefreshToken = jwtUtil.generateRefreshToken(username);

        tokenStore.storeToken(username, newRefreshToken);

        return Map.of(
                "accessToken", jwtUtil.generateToken(userDetails),
                "refreshToken", newRefreshToken
        );
    }

}

