package Arquitetura.Model.Enums;

public enum NivelAcesso {

    // -- Objetos Estáticos Finais -- //

    // ID 1
    N1("ACESSO BAIXO"),

    // ID 2
    N2("ACESSO MODERADO"),

    // ID 3
    N3("ACESSO TOTAL");

    // -- Atributos extras -- //
    private final String descricao;

    // -- Construtor -- //
    NivelAcesso(String descricao) {
        this.descricao = descricao;
    }

    // -- Getter -- //
    public String getDescricao() {
        return this.descricao;
    }


    // -- Métodos que checam o acesso -- //

    // Retorna true se o usuario tem acesso total (N3)
    public boolean temAcessoTotal() {

        return this == N3;
    }

    // Retorna true se o usuario tem acesso moderado (N3 e N2)
    public boolean temAcessoModerado() {

        return (this == N2 || this == N3);
    }

    // Retorna true se o usuario tem acesso moderado (N3, N2 e N1)
    public boolean temAcessoBaixo() {

        return (this == N1 || this == N2 || this == N3);
    }

}
