package br.com.harmoniacriativa.api.aluno;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AlunoDomain {
    private Long id;
    private String nomeAluno;
    private String email;
    private String cpfCnpj;
    private String telefone;
    private LocalDate dataNascimento;
    private String curso;
    private BigDecimal mensalidade;
    private LocalDate dataInicio;
    private List<HorarioAulaDomain> horariosAulas = new ArrayList<>();
    private String observacoes;
}
