package Arquitetura.Exception;

public class MedicoNaoHabilitadoException extends RuntimeException {
    public MedicoNaoHabilitadoException(String message) {
        super(message);
    }
}
