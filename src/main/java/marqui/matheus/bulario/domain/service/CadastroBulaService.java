package marqui.matheus.bulario.domain.service;

import lombok.RequiredArgsConstructor;
import marqui.matheus.bulario.domain.exception.BulaEmUsoException;
import marqui.matheus.bulario.domain.model.Bula;
import marqui.matheus.bulario.domain.repository.BulaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
@RequiredArgsConstructor
public class CadastroBulaService {
    private final BulaRepository bulas;

    public Bula salvar(Bula bula) {
        return bulas.save(bula);
    }

    public Bula atualizar(Long bulaId, Bula bula) {
        Bula bulaSalva = bulas.safeFindById(bulaId);

        bula.setId(bulaSalva.getId());

        return salvar(bula);
    }

    public void excluir(Long bulaId) {
        Bula bula = bulas.safeFindById(bulaId);
        try {
            bulas.delete(bula);
        } catch (DataIntegrityViolationException e) {
            throw new BulaEmUsoException("A bula de id %d não pode ser deletada pois está associada a um medicamento."
                    .formatted(bulaId));
        }
    }
}
