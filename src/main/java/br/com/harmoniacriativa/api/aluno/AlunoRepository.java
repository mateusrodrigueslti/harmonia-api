package br.com.harmoniacriativa.api.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByEmail(String email);

    // Busca todos os horários cadastrados ordenados por dia e hora
    @Query("SELECT h FROM HorarioAula h JOIN FETCH h.aluno ORDER BY h.diaSemana, h.horarioInicio")
    List<HorarioAula> findAllHorariosComAlunos();

    // Filtra a agenda por um dia da semana específico
    @Query("SELECT h FROM HorarioAula h JOIN FETCH h.aluno WHERE h.diaSemana = :diaSemana ORDER BY h.horarioInicio")
    List<HorarioAula> findAgendaPorDia(@Param("diaSemana") DiaSemana diaSemana);
}
