package com.example.rama.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll() // acceso libre
                .anyRequest().authenticated()              // todo lo demás requiere token
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt() // habilita validación de JWT
            );

        return http.build();
    }
}