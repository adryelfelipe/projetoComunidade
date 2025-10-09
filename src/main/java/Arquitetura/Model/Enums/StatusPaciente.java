package Arquitetura.Model.Enums;

public enum StatusPaciente {

    // -- Objetos Estáticos Finais -- //

    Ativo(1),

    Inativo(1);


    // -- Atributos Extras -- //

    private long idPaciente;


    // -- Construtor -- //

    StatusPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }


    // -- Getter -- //

    public long getIdPaciente()
    {
        return idPaciente;
    }
}


