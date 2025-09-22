package Arquitetura.Model;

import java.sql.Date;

public class Paciente extends Usuario{

    // Atributos
    private String contatoEmergencia;
    private String statusPaciente = "Ativo";
    private String numeroCarterinha;

    // Construtor
    public Paciente(String nome, String cpf, String senha, String sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String statusPaciente, String numeroCarterinha)
    {
        super(nome, cpf, senha, sexo, telefone, email,dataNascimento);
        this.contatoEmergencia = contatoEmergencia;
        this.statusPaciente = statusPaciente;
        this.numeroCarterinha = numeroCarterinha;
    }

    // Setters e Getters
    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    // Métodos

}
