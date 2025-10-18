package Arquitetura.Service.Validator;

import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

public class PacienteValidator {

    // -- Atributos -- //
    UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Métodos de verificação -- //
    public void verificarInsercaoDadosPaciente(Paciente paciente) {
        usuarioValidator.verificaRegrasInsercaoUsuario(paciente);

        if(paciente.getStatusPaciente() == null) {
            throw new DadosInvalidosException("ERRO! O STATUS DO PACIENTE NÃO PODE SER NULO");
        }

        if(paciente.getNumeroCarterinha() == null) {
            throw new DadosInvalidosException("ERRO! O NÚMERO DA CARTERINHA NÃO PODE SER NULA");
        }

        if(paciente.getContatoEmergencia() == null) {
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
