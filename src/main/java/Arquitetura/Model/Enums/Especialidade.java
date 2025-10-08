package Arquitetura.Model.Enums;

public enum Especialidade {

    // -- Objetos Estáticos -- //

    // ID 1
    CLINICO_GERAL("Responsável por: Hemograma, Glicemia, Colesterol, Sangue"),

    // ID 2
    CARDIOLOGISTA("Responsável por: Eletrocardiograma, Teste Ergométrico"),

    // ID 3
    RADIOLOGISTA("Responsável por: Raio-X"),

    // ID 4
    OTORRINOLARINGOLOGISTA("Responsável por: Audiometria, Áudio"),

    // ID 5
    OFTALMOLOGISTA("Responsável por: Visão"),

    // ID 6
    ENDOCRINOLOGISTA("Responsável por: Glicemia, Colesterol"),

    // ID 7
    HEMATOLOGISTA("Responsável por: Hemograma, Sangue");

    // -- Atributos extras -- //
    private String descricao;

    // -- Construtor -- //
    Especialidade(String descricao) {
        this.descricao = descricao;
    }

    // - Setters e Getters -- //
    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // -- Métodos -- //
    public boolean realizaHemograma() {
        return (this == CLINICO_GERAL || this == HEMATOLOGISTA);
    }

    public boolean realizaGlicemia() {
        return (this == CLINICO_GERAL || this == ENDOCRINOLOGISTA);
    }

    public boolean realizaColesterol() {
        return (this == CLINICO_GERAL || this == ENDOCRINOLOGISTA);
    }

    public boolean realizaRaioX() {
        return (this == RADIOLOGISTA);
    }

    public boolean realizaEletrocardio() {
        return (this == CARDIOLOGISTA);
    }

    public boolean realizaTesteErgo() {
        return (this == CARDIOLOGISTA);
    }

    public boolean realizaAudiometria() {
        return (this == OTORRINOLARINGOLOGISTA);
    }

    public boolean realizaAudio() {
        return (this == OTORRINOLARINGOLOGISTA);
    }

    public boolean realizaVisao() {
        return (this == OFTALMOLOGISTA);
    }

    public boolean realizaSangue() {
        return (this == CLINICO_GERAL || this == HEMATOLOGISTA);
    }
}
