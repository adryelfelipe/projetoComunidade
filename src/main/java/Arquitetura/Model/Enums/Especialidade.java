package Arquitetura.Model.Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Especialidade {

    // -- Objetos Estáticos -- //

    // ID 1
    CLINICO_GERAL("Responsável por: Hemograma, Glicemia, Colesterol, Sangue", 1, Exame.Hemograma, Exame.Glicemia, Exame.Colesterol, Exame.Sangue),

    // ID 2
    CARDIOLOGISTA("Responsável por: Eletrocardiograma, Teste Ergométrico", 2, Exame.Eletrocardiograma, Exame.TesteErgometrico),

    // ID 3
    RADIOLOGISTA("Responsável por: Raio-X",3, Exame.RaioX),

    // ID 4
    OTORRINOLARINGOLOGISTA("Responsável por: Audiometria, Áudio",4, Exame.Audiometria, Exame.Audio),

    // ID 5
    OFTALMOLOGISTA("Responsável por: Visão",5, Exame.Visao),

    // ID 6
    ENDOCRINOLOGISTA("Responsável por: Glicemia, Colesterol", 6, Exame.Glicemia, Exame.Colesterol),

    // ID 7
    HEMATOLOGISTA("Responsável por: Hemograma, Sangue", 7, Exame.Hemograma, Exame.Sangue);

    // -- Atributos extras -- //
    private String descricao;
    private final long idEspecialidade;
    private final List<Exame> exames;

    // -- Construtor -- //
    Especialidade(String descricao, long idEspecialidade, Exame... arrayExames)
    {
        this.descricao = descricao;
        this.idEspecialidade = idEspecialidade;

        // Converte uma array símples em uma Lista
        exames = Arrays.asList(arrayExames);
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
    public boolean podeRealizar(Exame exame) {

        return this.exames.contains(exame);
   }

   public void adicionarExame(Exame exame) {
        this.exames.add(exame);
   }
}
