package Arquitetura.Model.Enums;

public enum Especialidade {

    // -- Objetos Estáticos -- //

    // ID 1
    CLINICO_GERAL("Responsável por: Hemograma, Glicemia, Colesterol, Sangue", 1),

    // ID 2
    CARDIOLOGISTA("Responsável por: Eletrocardiograma, Teste Ergométrico", 2),

    // ID 3
    RADIOLOGISTA("Responsável por: Raio-X",3),

    // ID 4
    OTORRINOLARINGOLOGISTA("Responsável por: Audiometria, Áudio",4),

    // ID 5
    OFTALMOLOGISTA("Responsável por: Visão",5),

    // ID 6
    ENDOCRINOLOGISTA("Responsável por: Glicemia, Colesterol", 6),

    // ID 7
    HEMATOLOGISTA("Responsável por: Hemograma, Sangue", 7);

    // -- Atributos extras -- //
    private String descricao;
    private final long idEspecialidade;

    // -- Construtor -- //
    Especialidade(String descricao, long idEspecialidade) {
        this.descricao = descricao;
        this.idEspecialidade = idEspecialidade;
    }

    // - Setters e Getters -- //
    public String getDescricao() {

        return descricao;
    }

    public long getIdEspecialidade()
    {
        return idEspecialidade;
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
