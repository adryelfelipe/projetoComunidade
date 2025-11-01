package Arquitetura.Exception;

public class UsuarioInvalidoException extends RuntimeException {

    // -- Construtor -- //
    public UsuarioInvalidoException(String message) {
        super(message);
    }
}
