package marqui.matheus.bulario.api.controller;

import lombok.RequiredArgsConstructor;
import marqui.matheus.bulario.domain.exception.BulaEmUsoException;
import marqui.matheus.bulario.domain.exception.BulaNaoEncontradaException;
import marqui.matheus.bulario.domain.model.Bula;
import marqui.matheus.bulario.domain.repository.BulaRepository;
import marqui.matheus.bulario.domain.service.CadastroBulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bulas")
@RequiredArgsConstructor
public class BulaController {

    private final BulaRepository bulas;
    private final CadastroBulaService cadastroBula;

    @GetMapping
    private List<Bula> listar() {
        return bulas.findAll();
    }

    @GetMapping("/{bulaId}")
    private ResponseEntity<Bula> buscar(@PathVariable Long bulaId) {
        Optional<Bula> bula = bulas.findById(bulaId);

        return bula.isPresent() ?
                ResponseEntity.ok(bula.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Bula salvar(@RequestBody Bula bula) {
        return cadastroBula.salvar(bula);
    }

    @PutMapping("/{bulaId}")
    private ResponseEntity<Bula> atualizar(@PathVariable Long bulaId, @RequestBody Bula bula) {
        try {
            Bula bulaAtualizada = cadastroBula.atualizar(bulaId, bula);
            return ResponseEntity.ok(bula);
        } catch (BulaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bulaId}")
    private ResponseEntity<?> deletar(@PathVariable Long bulaId) {
        try {
            cadastroBula.excluir(bulaId);
            return ResponseEntity.noContent().build();
        } catch (BulaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (BulaEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }
}
