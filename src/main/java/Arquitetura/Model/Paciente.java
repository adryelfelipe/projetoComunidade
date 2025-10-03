package Arquitetura.Model;

import java.sql.Date;

public class Paciente extends Usuario {

    // -- Atributos -- //
    private String contatoEmergencia;
    private String statusPaciente = "Ativo";
    private String numeroCarterinha;
    private static final String tipoUsuario = "Paciente";

    // -- Construtores -- //

    // Não possui ID
    public Paciente(String nome, String cpf, String senha, String sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha)
    {
        super(nome, cpf, senha, sexo, telefone, email,dataNascimento);
        this.contatoEmergencia = contatoEmergencia;
        this.numeroCarterinha = numeroCarterinha;
    }

    // Possui ID
    public Paciente(long id, String nome, String cpf, String senha, String sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha) {
        this(nome, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numeroCarterinha);
        this.setId(id);
    }

    // -- Setters e Getters -- //
    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        if(!contatoEmergencia.isEmpty()) {
            this.contatoEmergencia = contatoEmergencia;
        }
    }

    public String getStatusPaciente() {
        return statusPaciente;
    }

    public void setStatusPaciente(String statusPaciente) {
        this.statusPaciente = statusPaciente;
    }

    public String getNumeroCarterinha() {
        return numeroCarterinha;
    }

    public void setNumeroCarterinha(String numeroCarterinha) {
        this.numeroCarterinha = numeroCarterinha;
    }

    @Override
    public String getTipoUsuario() {
        return Paciente.tipoUsuario;
    }
}
