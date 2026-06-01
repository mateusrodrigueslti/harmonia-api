package br.com.harmoniacriativa.api.aluno;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    Aluno map(AlunoDomain request);

    AlunoDomain map(AlunoRequest request);

    AlunoDomain map(Aluno source);

    List<AlunoDomain> map(List<Aluno> source);

    List<HorarioAula> mapList(List<HorarioAulaDomain> source);

    AlunoResponse mapToResponse(AlunoDomain aluno);

    @AfterMapping
    default void vincularHorariosAoAluno(AlunoDomain request, @MappingTarget Aluno aluno) {
        var horarios = aluno.getHorariosAulas();

        if (horarios != null) {
            horarios.forEach(horario -> horario.setAluno(aluno));
        }
    }

    default List<AgendaResponse> toAgendaResponseList(List<HorarioAula> horarios) {
        if (horarios == null) {
            return List.of();
        }

        var hoje = LocalDate.now();

        return horarios.stream().map(horario -> {
            // 1. Converte o seu Enum DiaSemana para o DayOfWeek nativo do Java
            var dayOfWeekJava = mapearParaDayOfWeek(horario.getDiaSemana());

            // 2. Descobre a data exata da próxima ocorrência desse dia da semana (ou hoje, se for o caso)
            var dataDoEvento = hoje.with(TemporalAdjusters.nextOrSame(dayOfWeekJava));

            // 3. Combina a data calculada com os horários de início e fim da aula
            var dataHoraInicio = LocalDateTime.of(dataDoEvento, horario.getHorarioInicio());
            var dataHoraFim = LocalDateTime.of(dataDoEvento, horario.getHorarioFim());

            // 4. Monta o título amigável para aparecer no card da agenda
            var titulo = horario.getAluno().getNomeAluno() + " - " + horario.getAluno().getCurso();

            return new AgendaResponse(
                horario.getId().toString(),
                titulo,
                dataHoraInicio,
                dataHoraFim
            );
        }).toList();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "horariosAulas", ignore = true)
    void updateEntityFromDto(AlunoUpdateRequest dto, @MappingTarget AlunoDomain entity);

    List<AlunoResponse> toResponseList(List<AlunoDomain> alunos);

    private DayOfWeek mapearParaDayOfWeek(DiaSemana diaSemana) {
        return switch (diaSemana) {
            case SEGUNDA -> DayOfWeek.MONDAY;
            case TERCA -> DayOfWeek.TUESDAY;
            case QUARTA -> DayOfWeek.WEDNESDAY;
            case QUINTA -> DayOfWeek.THURSDAY;
            case SEXTA -> DayOfWeek.FRIDAY;
            case SABADO -> DayOfWeek.SATURDAY;
            case DOMINGO -> DayOfWeek.SUNDAY;
        };
    }
}
