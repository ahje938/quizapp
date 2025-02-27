package com.example.questionservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deaktiver CSRF for API-kall
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/question/**").permitAll() // 🎯 Tillat GET uten
                                                                                         // autentisering
                        .anyRequest().authenticated() // Andre requests krever autentisering
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🎯
                                                                                                              // Ingen
                                                                                                              // sessions
                .formLogin(form -> form.disable()) // 🎯 Deaktiver login-side (hindrer redirect)
                .build();
    }
}
