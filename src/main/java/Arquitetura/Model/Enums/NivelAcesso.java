package Arquitetura.Model.Enums;

public enum NivelAcesso {

    // -- Objetos Estáticos Finais -- //

    // ACESSO BAIXO
    N1,

    // ACESSO MODERADO
    N2,

    // ACESSO TOTAL
    N3;

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
