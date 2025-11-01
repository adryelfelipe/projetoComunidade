package Arquitetura.Model.Enums;

public enum Departamento {

    // -- Objetos Estáticos Finais -- //

    // ID 1
    FINANCEIRO(1),

    // ID 2
    INFRAESTRUTURA(2),

    // ID 3
    MARKETING(3),

    // ID 4
    RH(4);

    // -- Atributos extras -- //
    private final long idDepartamento;

    // -- Construtor -- //
    Departamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    // -- Getters -- //
    public long getIdDepartamento() {
        return this.idDepartamento;
    }
}
