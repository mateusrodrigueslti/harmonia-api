package br.com.harmoniacriativa.api.aluno;

import java.time.LocalDateTime;

public record AgendaResponse(
    String id,
    String title,
    LocalDateTime start,
    LocalDateTime end
) { }
