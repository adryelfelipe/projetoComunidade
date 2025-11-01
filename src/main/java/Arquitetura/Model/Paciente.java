package Arquitetura.Model;

import Arquitetura.Model.Enums.Genero;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Enums.TipoUsuario;
import Arquitetura.Service.Validator.PacienteValidator;

import java.sql.Date;

public class Paciente extends Usuario {

    // -- Atributos -- //
    private String contatoEmergencia;
    private String numeroCadastro;
    private StatusPaciente statusPaciente;

    // -- Construtores -- //

    // Possui ID
    public Paciente(long id, String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCadastro, StatusPaciente statusPaciente) {
        super(TipoUsuario.PACIENTE,nome, cpf, senha, sexo, telefone, email,dataNascimento);
        this.setId(id);
        this.statusPaciente = statusPaciente;
        setContatoEmergencia(contatoEmergencia);
        setNumeroCadastro(numeroCadastro);
    }

    // Não possui ID
    public Paciente(String nome, String cpf, String senha, Genero sexo, String telefone, String email, Date dataNascimento, String contatoEmergencia, String numeroCadastro)
    {
        this(0, nome, cpf, senha, sexo, telefone, email, dataNascimento, contatoEmergencia, numeroCadastro, StatusPaciente.ATIVO);
    }

    // -- Setters e Getters -- //
    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        PacienteValidator.verificaIntegridadeContatoEmerg(contatoEmergencia);

        this.contatoEmergencia = contatoEmergencia;
    }

    public StatusPaciente getStatusPaciente() {
        return statusPaciente;
    }

    public void setStatusPaciente(StatusPaciente statusPaciente) {
        this.statusPaciente = statusPaciente;
    }

    public String getNumeroCadastro() {
        return numeroCadastro;
    }

    public void setNumeroCadastro(String numeroCadastro) {
        PacienteValidator.verificaIntegridadeNumeroCadastro(numeroCadastro);

        this.numeroCadastro = numeroCadastro;
    }

    @Override
    public void dadosPessoais() {
        super.dadosPessoais();
        System.out.println("CONTATO EMERGÊNCIA: " + contatoEmergencia);
        System.out.println("STATUS: " + statusPaciente);
        System.out.println("NÚMERO CARTEIRINHA: " + numeroCadastro);
    }
}
