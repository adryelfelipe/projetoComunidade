package Arquitetura.Exception;

public class UsuarioExistenteException extends RuntimeException {

    // -- Construtor -- //
    public UsuarioExistenteException(String message) {
        super(message);
    }
}
