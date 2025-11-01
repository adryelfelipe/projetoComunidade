package Arquitetura.Model.Enums;

import java.util.Arrays;
import java.util.List;

public enum Formacao {

    // -- Objetos Estáticos -- //
    MEDICINA("Graduação em Medicina", 1,
            Especialidade.CLINICO_GERAL,
            Especialidade.CARDIOLOGISTA,
            Especialidade.RADIOLOGISTA,
            Especialidade.OTORRINOLARINGOLOGISTA,
            Especialidade.OFTALMOLOGISTA,
            Especialidade.ENDOCRINOLOGISTA,
            Especialidade.HEMATOLOGISTA),

    ENFERMAGEM("Graduação em Enfermagem", 2,
            Especialidade.CLINICO_GERAL,
            Especialidade.HEMATOLOGISTA,
            Especialidade.ENDOCRINOLOGISTA,
            Especialidade.CARDIOLOGISTA),

    OFTALMOLOGISTA("Técnico em Oftalmologia", 3,
            Especialidade.CLINICO_GERAL,
            Especialidade.OFTALMOLOGISTA),

    RADIOLOGIA("Tecnólogo em Radiologia", 4,
            Especialidade.CLINICO_GERAL,
            Especialidade.RADIOLOGISTA),

    PATOLOGIA("Técnico em Patologia Clínica", 5,
            Especialidade.CLINICO_GERAL,
            Especialidade.HEMATOLOGISTA,
            Especialidade.ENDOCRINOLOGISTA);

    // -- Atributos -- //
    private final List<Especialidade> listaEspecialidades;
    private String descricao;
    private final int id;

    // -- Construtor -- //
    Formacao(String descricao, int id, Especialidade... especialidades) {
        this.listaEspecialidades = Arrays.asList(especialidades);
        this.descricao = descricao;
        this.id = id;
    }

    // -- Getters -- //
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Especialidade> getListaEspecialidades() {
        return listaEspecialidades;
    }

    public int getId() {
        return id;
    }
}
