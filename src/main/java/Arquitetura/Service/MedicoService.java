package Arquitetura.Service;

import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;

public class MedicoService {

    // -- Atributos -- //
    MedicoDAO medicoDAO = new MedicoDAO();
    UsuarioService usuarioService = new UsuarioService();
    FuncionarioService funcionarioService = new FuncionarioService();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Construtor -- //
    public MedicoService() {

    }

    // -- Métodos -- //

    // Verifica a veracidade dos atributos específicos de Medico
    public boolean verificarDadosMed(Medico medico) {
        return(medico.getFormacao() != null && medico.getEspecialidade() != null && medico.getPlantao() != null);
    }

    // Insere o objeto do tipo Medico no banco de dados
    public void inserirMedico(Administrador administrador, Medico medico) {
      if(verificarDadosMed(medico)) { // Verifica as regras para inserir um Medico
          if(funcionarioService.inserirFuncionario(administrador, medico)) {
              medicoDAO.inserirMedico(medico);
          }
      }
    }

    // Deleta medico da tabela Medico do banco de dados
    public boolean deletarMedico(Administrador administrador, Medico medico) {
        if(usuarioService.deletarUsuario(administrador, medico.getId())) {

            return medicoDAO.deletarMedico(medico.getId());
        }

        return false;
    }
}
