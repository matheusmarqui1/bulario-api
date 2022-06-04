package marqui.matheus.bulario.domain.repository;

import marqui.matheus.bulario.domain.exception.BulaNaoEncontradaException;
import marqui.matheus.bulario.domain.model.Bula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulaRepository extends JpaRepository<Bula, Long> {
    default Bula safeFindById(Long bulaId) {
        return findById(bulaId)
                .orElseThrow(
                        () -> new BulaNaoEncontradaException("Não foi possível encontrar a bula de id %d"
                                .formatted(bulaId)));
    }
}
