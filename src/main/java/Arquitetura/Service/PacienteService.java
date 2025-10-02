package Arquitetura.Service;

import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Paciente;

public class PacienteService {

    // -- Atributos -- //
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final UsuarioService usuarioService = new UsuarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // -- Construtor -- //
    public PacienteService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Paciente
    public boolean verificarDadosPac(Paciente paciente) {
        return (paciente.getStatusPaciente() != null && paciente.getContatoEmergencia() != null && paciente.getNumeroCarterinha() != null);
    }

    // Insere o objeto do tipo Paciente no banco de dados
    public void inserirPaciente(Administrador administrador, Paciente paciente) { // Verifica as regras para inserir um Paciente
        if(verificarDadosPac(paciente)) {
            if(usuarioService.inserirUsuario(administrador, paciente)) {
                pacienteDAO.inserirPaciente(paciente);
            }
        }
    }

    // Deleta paciente do banco de dados
    public boolean deletarPaciente(Administrador administrador, Paciente paciente) {

        if(usuarioService.deletarUsuario(paciente.getId())) {
            pacienteDAO.deletarPaciente(paciente.getId());
            usuarioDAO.deletarUsuario(paciente.getId());

            return true;
        }

        return false;
    }
}
