package br.com.harmoniacriativa.api.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {
    void deleteByAlunoId(Long alunoId);
}
