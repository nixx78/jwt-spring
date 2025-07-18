package lv.nixx.poc.jwt.controller;

import jakarta.servlet.http.HttpServletResponse;
import lv.nixx.poc.jwt.service.JwtAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtAuthService jwtAuthService;

    public AuthController(JwtAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {

        Map<String, String> tokens = jwtAuthService.authenticateAndGenerateTokenPairs(username, password);

        ResponseCookie cookie = ResponseCookie.from("accessToken", tokens.get("accessToken"))
                .httpOnly(true)
//                .secure(true)                // HTTPS only
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        try {
            return ResponseEntity.ok(jwtAuthService.refreshToken(refreshToken));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ex.getMessage());
        }
    }
}

