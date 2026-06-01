package br.com.harmoniacriativa.api.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AlunoRequest(
    @NotBlank(message = "O nome do aluno é obrigatório")
    String nomeAluno,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank(message = "CPF ou CNPJ é obrigatório")
    String cpfCnpj,

    String telefone,

    @NotNull(message = "A data de nascimento é obrigatória")
    LocalDate dataNascimento,

    @NotBlank(message = "O curso é obrigatório")
    String curso,

    @NotNull(message = "A mensalidade é obrigatória")
    BigDecimal mensalidade,

    @NotNull(message = "A data de início é obrigatória")
    LocalDate dataInicio,

    @NotEmpty(message = "É necessário informar ao menos um horário de aula")
    List<HorarioRequest> horariosAulas,

    String observacoes
) {
    // Record auxiliar embutido para mapear os horários que vêm no JSON
    public record HorarioRequest(
        @NotNull(message = "O dia da semana é obrigatório")
        DiaSemana diaSemana,

        @NotNull(message = "O horário de início é obrigatório")
        LocalTime horarioInicio,

        @NotNull(message = "O horário de término é obrigatório")
        LocalTime horarioFim
    ) { }
}
