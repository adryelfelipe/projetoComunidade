package Arquitetura.Service;

import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Exception.CpfInvalidoException;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Paciente;
import Arquitetura.Model.Usuario;
import Arquitetura.Service.Validator.PacienteValidator;
import Arquitetura.Service.Validator.TipoUsuarioValidator;
import Arquitetura.Service.Validator.UsuarioValidator;

public class PacienteService {

    // -- Atributos -- //
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final TipoUsuarioValidator tipoUsuarioValidator = new TipoUsuarioValidator();
    private final PacienteValidator pacienteValidator = new PacienteValidator();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();

    // -- Construtor -- //
    public PacienteService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Paciente
    private boolean verificarDadosPac(Paciente paciente) {
        return (paciente.getStatusPaciente() != null && paciente.getContatoEmergencia() != null && paciente.getNumeroCarterinha() != null);
    }

    public void inserirPaciente(Usuario usuario, Paciente paciente) {
        // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);
        usuarioValidator.verificaRegrasInsercaoUsuario(paciente);
        pacienteValidator.verificarDadosPaciente(paciente);

        // Insere nessa ordem para respeitar as chaves estrangeiras
        usuarioDAO.inserirUsuario(paciente);
        pacienteDAO.inserirPaciente(paciente);
    }

    public void deletarPaciente(Usuario usuario, String cpfPacienteDeletado) {
       // Verificações de dados
        tipoUsuarioValidator.temAcessoTotal(usuario);

        if(!usuarioService.isCpfExistente(cpfPacienteDeletado)) {
            throw new CpfInvalidoException("ERRO! CPF INVÁLIDO");
        }

        // Deleta nessa ordem para respeitar as chaves estrangeiras
        pacienteDAO.deletarPaciente(cpfPacienteDeletado);
        usuarioDAO.deletarUsuario(cpfPacienteDeletado);
    }
}
