package Arquitetura.Service;

import Arquitetura.Dao.PacienteDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Paciente;

public class PacienteService {

    // -- Atributos -- //
    PacienteDAO pacienteDAO = new PacienteDAO();
    UsuarioService usuarioService = new UsuarioService();

    // -- Construtor -- //
    public PacienteService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Paciente
    public boolean verificarDadosPac(Paciente paciente) {
        return (paciente.getStatusPaciente() != null && paciente.getContatoEmergencia() != null && paciente.getNumeroCarterinha() != null);
    }

    // Insere o objeto do tipo Paciente no banco de dados
    public void inserirPaciente(Administrador administrador, Paciente paciente) {
        if(verificarDadosPac(paciente)) {
            if(usuarioService.inserirUsuario(administrador, paciente)) {
                pacienteDAO.inserirPaciente(paciente);
            }
        }
    }
}
