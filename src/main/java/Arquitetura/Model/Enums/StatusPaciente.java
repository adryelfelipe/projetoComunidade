package Arquitetura.Model.Enums;

public enum StatusPaciente {

    // -- Objetos Estáticos Finais -- //

    ATIVO(1),

    INATIVO(2);


    // -- Atributos Extras -- //

    private long idStatusPaciente;


    // -- Construtor -- //

    StatusPaciente(long idStatusPaciente) {
        this.idStatusPaciente = idStatusPaciente;
    }


    // -- Getter -- //

    public long getIdPaciente()
    {
        return idStatusPaciente;
    }
}


