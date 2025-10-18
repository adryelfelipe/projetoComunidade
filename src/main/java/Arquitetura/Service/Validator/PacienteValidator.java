package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

public class PacienteValidator {

    // -- Atributos -- //
    UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Métodos verificadores de regras de negócio -- //
    public void verificarInsercaoDadosPaciente(Paciente paciente) {
        usuarioValidator.verificaRegrasInsercaoUsuario(paciente);
        verificaRegrasNumeroCarterinha(paciente.getNumeroCarterinha());
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
        if(contatoEmergencia == null) {
            throw new DadosInvalidosException("ERRO! O CONTATO DE EMERGÊNCIA NÃO PODE SER NULO");
        }
    }

    // -- Métodos verificadores de integridade de dados -- //
    public void verificaIntegridadeContatoEmerg(String contatoEmergencia) {
        if(contatoEmergencia.isBlank()) {
            throw new DadosInvalidosException("ERRO! O CONTATO DE EMERGÊNCIA NÃO PODE SER VAZIO");
        }
    }

    public void verificaIntegridadeNumeroCarterinha(String numeroCarterinha) {
        if(numeroCarterinha.isBlank()) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DA CARTERINHA NÃO PODE SER VAZIO");
        }
    }
}
