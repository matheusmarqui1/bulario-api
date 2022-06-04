package marqui.matheus.bulario.api.controller;

import lombok.RequiredArgsConstructor;
import marqui.matheus.bulario.domain.exception.BulaNaoEncontradaException;
import marqui.matheus.bulario.domain.exception.MedicamentoNaoEncontradoException;
import marqui.matheus.bulario.domain.model.Medicamento;
import marqui.matheus.bulario.domain.repository.MedicamentoRepository;
import marqui.matheus.bulario.domain.service.CadastroMedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoRepository medicamentos;
    private final CadastroMedicamentoService cadastroMedicamento;

    @GetMapping
    public List<Medicamento> listar() {
        return medicamentos.findAll();
    }

    @GetMapping("/{medicamentoId}")
    public ResponseEntity<Medicamento> buscar(@PathVariable Long medicamentoId) {
        Optional<Medicamento> medicamento = medicamentos.findById(medicamentoId);

        return medicamento.isPresent() ?
                ResponseEntity.ok(medicamento.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Medicamento medicamento) {
        try{
            Medicamento medicamentoSalvo = cadastroMedicamento.salvar(medicamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoSalvo);
        }catch (BulaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{medicamentoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long medicamentoId,
                                       @RequestBody Medicamento medicamento) {
        try {
            Medicamento medicamentoAtualizado = cadastroMedicamento.atualizar(medicamentoId, medicamento);
            return ResponseEntity.ok(medicamentoAtualizado);
        } catch (BulaNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (MedicamentoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{medicamentoId}")
    public ResponseEntity<Medicamento> excluir(@PathVariable Long medicamentoId) {
        try {
            cadastroMedicamento.excluir(medicamentoId);
            return ResponseEntity.noContent().build();
        } catch (MedicamentoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
