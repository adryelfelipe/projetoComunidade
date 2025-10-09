package Arquitetura.Model.Enums;

public enum Plantao {

    // -- Objetos Estáticos Finais -- //

    // ID 1
    MATUTINO(1),

    // ID 2
    VERPERTINO(2),

    // ID 3
    NOTURNO(3);

    // -- Atributos extras -- //
    long idPlantao;

    // -- Construtor -- //
    Plantao(long idPlantao) {
        this.idPlantao = idPlantao;
    }

    // -- Getter -- //
    public long getIdPlantao() {
        return idPlantao;
    }
}
