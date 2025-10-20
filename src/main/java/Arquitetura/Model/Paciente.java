package Arquitetura.Model;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Service.UsuarioService;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

import java.sql.Date;

public class Paciente extends Usuario {

    // -- Atributos -- //
    private String contatoEmergencia;
    private String numeroCarterinha;
    private StatusPaciente statusPaciente;

    // -- Construtores -- //

    // Possui ID
    public Paciente(long id, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha, StatusPaciente statusPaciente) {
        super(TipoUsuario.PACIENTE,nome, cpf, senha, sexo, telefone, email,dataNascimento);
        this.setId(id);
        this.statusPaciente = statusPaciente;
        setContatoEmergencia(contatoEmergencia);
        setNumeroCarterinha(numeroCarterinha);
    }

    // Não possui ID
    public Paciente(String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCarterinha)
    {
        this(0, nome, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numeroCarterinha, StatusPaciente.ATIVO);
    }

    // -- Setters e Getters -- //
    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        pacienteValidator.verificaIntegridadeContatoEmerg(contatoEmergencia);

        this.contatoEmergencia = contatoEmergencia;
    }

    public StatusPaciente getStatusPaciente() {
        return statusPaciente;
    }

    public void setStatusPaciente(StatusPaciente statusPaciente) {
        this.statusPaciente = statusPaciente;
    }

    public String getNumeroCarterinha() {
        return numeroCarterinha;
    }

    public void setNumeroCarterinha(String numeroCarterinha) {
        pacienteValidator.verificaIntegridadeNumeroCarterinha(numeroCarterinha);

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
