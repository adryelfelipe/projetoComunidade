package Arquitetura.Service.Validator;

import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Exception.DadosInvalidosException;
import Arquitetura.Exception.IdInvalidoException;
import Arquitetura.Model.Enums.StatusPaciente;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.PacienteService;

public class PacienteValidator {

    // -- Construtor -- //
    public PacienteValidator(UsuarioValidator usuarioValidator, PacienteService pacienteService) {
        this.usuarioValidator = usuarioValidator;
        this.pacienteService = pacienteService;
    }

    // -- Atributos -- //
    private final UsuarioValidator usuarioValidator;
    private final PacienteService pacienteService;

    // -- Métodos de análise -- //
    public void idPacienteValidator(long id) {
        if(!pacienteService.isIdPaciente(id)) {
            throw new IdInvalidoException("ERRO! O ID INFORMADO NÃO É DE UM PACIENTE");
        }
    }

    public void cpfPacienteValidator (String cpf)
    {
        if(!pacienteService.isCpfPaciente(cpf))
        {
            throw new CpfInvalidoException("ERRO ! CPF NÃO PERTENCE A UM PACIENTE");
        }
    }

    // -- Métodos verificadores de regras de negócio -- //
    public void verificaRegrasInsercaoPaciente(Paciente paciente) {
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
