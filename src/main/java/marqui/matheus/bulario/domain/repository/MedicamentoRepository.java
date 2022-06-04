package marqui.matheus.bulario.domain.repository;

import marqui.matheus.bulario.domain.exception.MedicamentoNaoEncontradoException;
import marqui.matheus.bulario.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    default Medicamento safeFindById(Long medicamentoId) {
        return findById(medicamentoId)
                .orElseThrow(
                        () -> new MedicamentoNaoEncontradoException("Não foi possível encontrar a bula de id %d"
                                .formatted(medicamentoId)));
    }
}
