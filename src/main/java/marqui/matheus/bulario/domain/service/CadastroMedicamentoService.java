package marqui.matheus.bulario.domain.service;

import lombok.RequiredArgsConstructor;
import marqui.matheus.bulario.domain.exception.BulaNaoEncontradaException;
import marqui.matheus.bulario.domain.exception.MedicamentoNaoEncontradoException;
import marqui.matheus.bulario.domain.model.Bula;
import marqui.matheus.bulario.domain.model.Medicamento;
import marqui.matheus.bulario.domain.repository.BulaRepository;
import marqui.matheus.bulario.domain.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroMedicamentoService {

    private final MedicamentoRepository medicamentos;
    private final BulaRepository bulas;

    public Medicamento salvar(Medicamento medicamento) {
        Bula bula = bulas.safeFindById(medicamento.getBula().getId());
        medicamento.setBula(bula);
        return medicamentos.save(medicamento);
    }

    public Medicamento atualizar(Long medicamentoId, Medicamento medicamento) {
        Medicamento medicamentoSalvo = medicamentos.safeFindById(medicamentoId);
        Bula bula = bulas.safeFindById(medicamento.getBula().getId());

        medicamento.setId(medicamentoSalvo.getId());
        medicamento.setBula(bula);

        return medicamentos.save(medicamento);
    }

    public void excluir(Long medicamentoId) {
        Medicamento medicamento = medicamentos.safeFindById(medicamentoId);
        medicamentos.delete(medicamento);
    }
}
