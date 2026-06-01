package br.com.harmoniacriativa.api.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // TEMPORÁRIO
        // depois você valida no banco
        if (request.email().equals("admin@harmonia.com") && request.password().equals("123456")) {

            return new AuthResponse("jwt-token-fake");
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}
