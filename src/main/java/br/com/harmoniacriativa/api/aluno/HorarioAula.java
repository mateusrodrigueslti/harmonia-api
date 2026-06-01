package br.com.harmoniacriativa.api.aluno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "horarios_aulas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O dia da semana é obrigatório")
    @Enumerated(EnumType.STRING) // Garante que salvará "SEGUNDA", "TERCA" no banco
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;

    @NotNull(message = "O horário de início é obrigatório")
    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @NotNull(message = "O horário de término é obrigatório")
    @Column(name = "horario_fim", nullable = false)
    private LocalTime horarioFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
