package marqui.matheus.bulario.domain.exception;

public class MedicamentoNaoEncontradoException extends RuntimeException {
    public MedicamentoNaoEncontradoException(String message) {
        super(message);
    }
}
