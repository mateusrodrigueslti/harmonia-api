package br.com.harmoniacriativa.api.auth;

public record AuthRequest(
    String email,
    String password
) { }
