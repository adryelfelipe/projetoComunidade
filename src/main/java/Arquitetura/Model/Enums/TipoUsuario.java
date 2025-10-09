package Arquitetura.Model.Enums;

public enum TipoUsuario {

    // -- Objetos Estáticos Finais -- //

    // ID 1
    ADMIN(NivelAcesso.N3, 1),

    // ID 2
    MEDICO(NivelAcesso.N2, 2),

    // ID 3
    PACIENTE(NivelAcesso.N1, 3);

    // -- Atributos extras -- //
    private final NivelAcesso nivelAcesso;
    private final long idTipoUsuario;

    // -- Construtor -- //
    TipoUsuario(NivelAcesso nivelAcesso, long idTipoUsuario) {
        this.nivelAcesso = nivelAcesso;
        this.idTipoUsuario = idTipoUsuario;
    }

    // -- Getters -- //
    public NivelAcesso getNivelAcesso() {

        return this.nivelAcesso;
    }

    public long getIdTipoUsuario() {
        return idTipoUsuario;
    }
}
