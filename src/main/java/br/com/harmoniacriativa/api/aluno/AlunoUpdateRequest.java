package br.com.harmoniacriativa.api.aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record AlunoUpdateRequest(
    @NotBlank String nomeAluno,
    @NotBlank String email,
    @NotBlank String telefone,
    @NotNull BigDecimal mensalidade,
    @NotBlank String curso,
    @Valid List<AlunoRequest.HorarioRequest> horariosAulas,
    String observacoes
) { }
