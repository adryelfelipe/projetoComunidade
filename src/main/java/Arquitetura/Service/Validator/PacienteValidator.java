package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;

public class PacienteValidator {

    // -- Atributos -- //
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoPaciente(Paciente paciente) {
        usuarioValidator.verificaRegrasInsercaoUsuario(paciente);
        verificaRegrasNumeroCarterinha(paciente.getNumeroCadastro());
        verificaRegrasContatoEmergencia(paciente.getContatoEmergencia());
        verificaRegrasStatusPaciente(paciente.getStatusPaciente());
    }

    public void verificaRegrasStatusPaciente(StatusPaciente statusPaciente) {
        if(statusPaciente == null) {
            throw new DadosInvalidosException("ERRO! O STATUS DO PACIENTE NÃO PODE SER NULO");
        }
    }

    public void verificaRegrasNumeroCarterinha(String numeroCarterinha) {
        if(numeroCarterinha == null) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DA CARTERINHA NÃO PODE SER NULA");
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

    public static void verificaIntegridadeNumeroCarterinha(String numeroCarterinha) {
        if(numeroCarterinha.isBlank()) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DA CARTERINHA NÃO PODE SER VAZIO");
        }
    }
}
