package Arquitetura.Model.Enums;


public enum Status
{
    //Status de Agendamento

    //ID 1
    AGENDADA(1),

    //ID 2
    REAGENDADA(2),

    //Status de Ocorrência

    //ID 3
    AGUARDANDO(3),

    //ID 4
    EM_ATENDIMENTO(4),

    //Status de Finalização

    //ID 5
    REALIZADA(5),

    //ID 6
    CANCELADA(6),

    //ID 7
    FALTA(7);

    // -- Atributos extras -- //
    private int idStatus;

    // -- Construtor -- //
    Status(int idStatus) {this.idStatus = idStatus;}

    // -- Getter - //
    public int getIdStatus() {return idStatus;}

}
