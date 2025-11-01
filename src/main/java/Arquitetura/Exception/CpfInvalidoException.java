package Arquitetura.Exception;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String message) {
        super(message);
    }
}
