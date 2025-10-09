package Arquitetura.Model;

import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.TipoUsuario;

import java.sql.Date;

public class Paciente extends Usuario {

    // -- Atributos -- //
    private String contatoEmergencia;
    private String statusPaciente = "Ativo";
    private String numeroCarterinha;
    private static final String tipoUsuario = "Paciente";

    // -- Construtores -- //

    // Possui ID
    public Paciente(long id, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha) {
        super(TipoUsuario.PACIENTE,nome, cpf, senha, sexo, telefone, email,dataNascimento);
        this.setId(id);
        setContatoEmergencia(contatoEmergencia);
        setNumeroCarterinha(numeroCarterinha);
    }

    // Não possui ID
    public Paciente(String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha)
    {
        this(0, nome, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numeroCarterinha);
    }

    // -- Setters e Getters -- //
    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        if(contatoEmergencia.isEmpty()) {
            throw new IllegalArgumentException("ERRO! CONTATO DE EMERGÊNCIA NÃO PODE SER VAZIO");
        }

        this.contatoEmergencia = contatoEmergencia;
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
        if(numeroCarterinha.isEmpty()) {
            throw new IllegalArgumentException("ERRO! NÚMERO DA CARTERINHA NÃO PODE SER VAZIO");
        }

        this.numeroCarterinha = numeroCarterinha;
    }

    @Override
    public void dadosPessoais() {
        super.dadosPessoais();
        System.out.println("CONTATO EMERGÊNCIA: " + contatoEmergencia);
        System.out.println("STATUS: " + statusPaciente);
        System.out.println("NÚMERO CARTEIRINHA: " + numeroCarterinha);
    }
}
