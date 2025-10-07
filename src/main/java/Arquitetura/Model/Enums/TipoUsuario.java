package Arquitetura.Model.Enums;

public enum TipoUsuario {

    // -- Objetos Estáticos Finais -- //

    // ID 1
    ADMIN(NivelAcesso.N3),

    // ID 2
    MEDICO(NivelAcesso.N2),

    // ID 3
    PACIENTE(NivelAcesso.N1);

    // -- Atributos extras -- //
    private final NivelAcesso nivelAcesso;

    // -- Construtor -- //
    TipoUsuario(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    // -- Getter -- //
    public NivelAcesso getNivelAcesso() {

        return this.nivelAcesso;
    }
}
