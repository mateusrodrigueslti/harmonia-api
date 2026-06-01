package br.com.harmoniacriativa.api.aluno;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoMapper alunoMapper;

    @PostMapping
    public ResponseEntity<Object> cadastrarAluno(@Valid @RequestBody AlunoRequest request) {
        try {
            var alunoSalvo = alunoService.cadastrar(alunoMapper.map(request));

            return ResponseEntity.status(HttpStatus.CREATED).body(alunoMapper.map(alunoSalvo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodosAlunos() {
        var alunos = alunoService.listarTodos();

        return ResponseEntity.ok(alunoMapper.toResponseList(alunos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarAlunoPorId(@PathVariable Long id) {
        try {
            var aluno = alunoService.buscarPorId(id);

            return ResponseEntity.ok(alunoMapper.mapToResponse(aluno));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAluno(@PathVariable Long id, @Valid @RequestBody AlunoUpdateRequest request) {
        try {
            var alunoAtualizado = alunoService.atualizar(id, request);

            return ResponseEntity.ok(alunoMapper.map(alunoAtualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
