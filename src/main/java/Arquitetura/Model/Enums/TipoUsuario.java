package Arquitetura.Model.Enums;

public enum TipoUsuario {

    // -- Objetos Estáticos Finais -- //
    ADMIN(NivelAcesso.N3),
    MEDICO(NivelAcesso.N2),
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
