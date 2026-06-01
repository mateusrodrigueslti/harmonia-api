package br.com.harmoniacriativa.api.aluno;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class HorarioAulaDomain {
    private Long id;
    private Long alunoId;
    private DiaSemana diaSemana;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
}
