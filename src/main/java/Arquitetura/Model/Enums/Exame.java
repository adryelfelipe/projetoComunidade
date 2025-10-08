package Arquitetura.Model.Enums;

public enum Exame {

    // -- Objetos Estáticos -- //

    // ID 1
    Hemograma("Avalia as células do sangue", 45.00, 1),

    // ID 2
    Glicemia("Mede a concentração de glicose no sangue", 15.00, 2),

    // ID 3
    Colesterol("Mede os níveis de lipídios no sangue", 50.00, 3),

    // ID 4
    RaioX("Exame de imagem que utiliza radiação para visualizar ossos e órgãos", 120.00, 4),

    // ID 5
    Eletrocardiograma("Registra a atividade elétrica do coração em repouso", 85.00, 5),

    // ID 6
    TesteErgometrico("Avalia o funcionamento do coração durante esforço físico", 250.00, 6),

    // ID 7
    Audiometria("Avalia a capacidade de uma pessoa ouvir sons e a fala", 130.00, 7),

    // ID 8
    Audio("Teste genérico relacionado à avaliação da audição", 100.00, 8),

    // ID 9
    Visao("Teste genérico relacionado à acuidade visual e saúde dos olhos", 70.00, 9),

    // ID 10
    Sangue("Teste genérico para análise de amostra sanguínea", 20.00, 10);

    // -- Atributos extras -- //
    private String descricao;
    private double valor;
    private final long idExame;

    // -- Construtor -- //
    Exame(String descricao, double valor, long idExame) {
        this.descricao = descricao;
        this.valor = valor;
        this.idExame = idExame;
    }

    // -- Getters -- //
    public String getDescricao() {
        return descricao;
    }

    public long getIdExame() {
        return idExame;
    }

    public double getValor() {
        return valor;
    }
}