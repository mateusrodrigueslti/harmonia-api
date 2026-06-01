package br.com.harmoniacriativa.api.aluno;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    public static final String ALUNO_NAO_ENCONTRADO_COM_O_ID = "Aluno não encontrado com o ID: {}";

    private final AlunoRepository alunoRepository;
    private final HorarioAulaRepository horarioAulaRepository;
    private final AlunoMapper alunoMapper;

    @Transactional
    public AlunoDomain cadastrar(AlunoDomain request) {
        var alunoExistente = alunoRepository.findByEmail(request.getEmail());

        if (alunoExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este e-mail.");
        }

        var alunoSalvo = alunoMapper.map(alunoRepository.save(alunoMapper.map(request)));

        if (request.getHorariosAulas() != null) {
            var horarios = request.getHorariosAulas().stream().map(dto -> {
                var horario = new HorarioAulaDomain();
                horario.setDiaSemana(dto.getDiaSemana());
                horario.setHorarioInicio(dto.getHorarioInicio());
                horario.setHorarioFim(dto.getHorarioFim());
                horario.setAlunoId(alunoSalvo.getId());

                return horario;
            }).toList();

            horarioAulaRepository.saveAll(alunoMapper.mapList(horarios));
        }

        return alunoSalvo;
    }

    @Transactional
    public AlunoDomain atualizar(Long id, AlunoUpdateRequest request) {
        var alunoIdoso = alunoRepository.findById(id)
            .map(alunoMapper::map)
            .orElseThrow(() -> new IllegalArgumentException(ALUNO_NAO_ENCONTRADO_COM_O_ID + id));

        if (!alunoIdoso.getEmail().equals(request.email())) {
            var emailEmUso = alunoRepository.findByEmail(request.email());

            if (emailEmUso.isPresent()) {
                throw new IllegalArgumentException("Este e-mail já está sendo usado por outro aluno.");
            }
        }

        // 1. Atualiza os dados cadastrais da raiz
        alunoMapper.updateEntityFromDto(request, alunoIdoso);
        var alunoAtualizado = alunoMapper.map(alunoRepository.save(alunoMapper.map(alunoIdoso)));

        // 2. CONTROLE MANUAL: Deleta absolutamente todos os horários antigos direto no banco
        horarioAulaRepository.deleteByAlunoId(id);

        // 3. Grava a nova lista de horários do zero
        if (request.horariosAulas() != null) {
            var novosHorarios = request.horariosAulas().stream().map(dto -> {
                var horario = new HorarioAulaDomain();
                horario.setDiaSemana(dto.diaSemana());
                horario.setHorarioInicio(dto.horarioInicio());
                horario.setHorarioFim(dto.horarioFim());
                horario.setAlunoId(alunoAtualizado.getId());

                return horario;
            }).toList();

            horarioAulaRepository.saveAll(alunoMapper.mapList(novosHorarios));
        }

        return alunoAtualizado;
    }

    @Transactional(readOnly = true)
    public List<AlunoDomain> listarTodos() {
        return alunoMapper.map(alunoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AlunoDomain buscarPorId(Long id) {
        return alunoRepository.findById(id)
            .map(alunoMapper::map)
            .orElseThrow(() -> new IllegalArgumentException(ALUNO_NAO_ENCONTRADO_COM_O_ID + id));
    }
}
