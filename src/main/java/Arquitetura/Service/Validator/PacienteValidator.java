package Arquitetura.Service.Validator;

import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;

public class PacienteValidator {

    // -- Métodos de verificação -- //
    public void verificarDadosPaciente(Paciente paciente) {
        if(paciente.getStatusPaciente() == null) {
            throw new IllegalArgumentException("ERRO! O STATUS DO PACIENTE NÃO PODE SER NULO");
        }

        if(paciente.getNumeroCarterinha() == null) {
            throw new IllegalArgumentException("ERRO! O NÚMERO DA CARTERINHA NÃO PODE SER NULO");
        }

        if(paciente.getContatoEmergencia() == null) {
            throw new IllegalArgumentException("ERRO! O CONTATO DE EMERGÊNCIA NÃO PODE SER NULO");
        }
    }
}
