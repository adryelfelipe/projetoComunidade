package Arquitetura.Model.Enums;

public enum Exame {

    // -- Objetos Estáticos -- //

    // ID 1
    Hemograma("Avalia as células do sangue", 45.00),

    // ID 2
    Glicemia("Mede a concentração de glicose no sangue", 15.00),

    // ID 3
    Colesterol("Mede os níveis de lipídios no sangue", 50.00),

    // ID 4
    RaioX("Exame de imagem que utiliza radiação para visualizar ossos e órgãos", 120.00),

    // ID 5
    Eletrocardiograma("Registra a atividade elétrica do coração em repouso", 85.00),

    // ID 6
    TesteErgometrico("Avalia o funcionamento do coração durante esforço físico", 250.00),

    // ID 7
    Audiometria("Avalia a capacidade de uma pessoa ouvir sons e a fala", 130.00),

    // ID 8
    Audio("Teste genérico relacionado à avaliação da audição", 100.00),

    // ID 9
    Visao("Teste genérico relacionado à acuidade visual e saúde dos olhos", 70.00),

    // ID 10
    Sangue("Teste genérico para análise de amostra sanguínea", 20.00);

    // -- Atributos extras -- //
    private String descricao;
    private double valor;

    // -- Construtor -- //
    Exame(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
}