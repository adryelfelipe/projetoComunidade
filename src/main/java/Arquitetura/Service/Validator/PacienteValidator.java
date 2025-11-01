package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

public class PacienteValidator {

    // -- Atributos -- //
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoPaciente(Usuario usuarioInsersor, Paciente paciente) {
        usuarioValidator.verificaRegrasInsercaoUsuario(usuarioInsersor, paciente);
        verificaRegrasNumeroCadastro(paciente.getNumeroCadastro());
        verificaRegrasContatoEmergencia(paciente.getContatoEmergencia());
        verificaRegrasStatusPaciente(paciente.getStatusPaciente());
    }

    public void verificaRegrasStatusPaciente(StatusPaciente statusPaciente) {
        if(statusPaciente == null) {
            throw new DadosInvalidosException("ERRO! O STATUS DO PACIENTE NÃO PODE SER NULO");
        }
    }

    public void verificaRegrasNumeroCadastro(String numeroCadastro) {
        if(numeroCadastro == null) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DO CADASTRO NÃO PODE SER NULO");
        }

        if(numeroCadastro.length() != 5) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DO CADASTRO DEVE CONTER 5 DÍGITOS");
        }
    }

    public void verificaRegrasContatoEmergencia(String contatoEmergencia) {
        usuarioValidator.verificarRegrasTelefone(contatoEmergencia);
    }

    // -- Métodos verificadores de integridade de dados -- //
    public static void verificaIntegridadeContatoEmerg(String contatoEmergencia) {
        if(contatoEmergencia.isBlank()) {
            throw new DadosInvalidosException("ERRO! O CONTATO DE EMERGÊNCIA NÃO PODE SER VAZIO");
        }
    }

    public static void verificaIntegridadeNumeroCadastro(String numeroCarterinha) {
        if(numeroCarterinha.isBlank()) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DA CARTERINHA NÃO PODE SER VAZIO");
        }
    }
}
