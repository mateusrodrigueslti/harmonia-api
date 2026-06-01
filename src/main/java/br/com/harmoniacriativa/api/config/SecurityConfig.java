package br.com.harmoniacriativa.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Mantém desabilitado se for uma API REST stateless
            .authorizeHttpRequests(auth -> auth
                // Libera absolutamente qualquer requisição sem validação
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
