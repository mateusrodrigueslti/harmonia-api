package br.com.harmoniacriativa.api.aluno;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AlunoResponse(
    String nomeAluno,
    String email,
    String cpfCnpj,
    String telefone,
    LocalDate dataNascimento,
    String curso,
    BigDecimal mensalidade,
    LocalDate dataInicio,
    List<HorarioRequest> horariosAulas,
    String observacoes
) {
    public record HorarioRequest(
        DiaSemana diaSemana,
        LocalTime horarioInicio,
        LocalTime horarioFim
    ) { }
}
