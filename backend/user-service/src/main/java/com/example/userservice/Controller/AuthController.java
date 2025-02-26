package com.example.userservice.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.Model.User;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Security.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ✅ Brukerregistrering med kryptert passord
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Brukernavn er allerede i bruk!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse); // Endret til 409 Conflict
        }

        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 🚀 Krypter passord
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Bruker registrert!");
        return ResponseEntity.ok(response);
    }

    // ✅ Innlogging med JWT i HttpOnly-cookie
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user, HttpServletResponse response) {
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());

        // ✅ Sjekker om brukeren eksisterer og passordet er riktig
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            String token = jwtUtil.generateToken(existingUser.get().getUserName());

            // ✅ Legger JWT i HttpOnly-cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Må bruke HTTPS i produksjon!
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60); // 1 time

            response.addCookie(cookie);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Innlogging vellykket!");
            return ResponseEntity.ok(responseBody);
        }

        // ❌ Feil innlogging: Returner 401 Unauthorized
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Feil brukernavn eller passord!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    // ✅ Validerer JWT-token fra cookie
    @GetMapping("/validatetoken")
    public ResponseEntity<Map<String, String>> validateToken(
            @CookieValue(name = "jwt", required = false) String token) {
        Map<String, String> response = new HashMap<>();

        if (token == null || !jwtUtil.validateToken(token)) {
            response.put("error", "Ugyldig eller utløpt token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        response.put("message", "Token er gyldig");
        return ResponseEntity.ok(response);
    }

    // ✅ Logg ut brukeren (slett token-cookie)
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Sletter cookie

        response.addCookie(cookie);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Logget ut!");
        return ResponseEntity.ok(responseBody);
    }
}
