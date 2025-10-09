package Arquitetura.Model.Enums;

public enum Genero {

    // -- Objetos Estáticos Finais -- //

    // ID 1
    MASCULINO(1),

    // ID 2
    FEMININO(2);

    // -- Atributos extras -- //
    private final long idGenero;

    // -- Construtor -- //
    Genero(long idGenero) {
        this.idGenero = idGenero;
    }

    // -- Getters -- //
    public long getIdGenero() {
        return idGenero;
    }
}
