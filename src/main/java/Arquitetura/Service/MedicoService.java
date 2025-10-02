package Arquitetura.Service;

import Arquitetura.Dao.FuncionarioDAO;
import Arquitetura.Dao.MedicoDAO;
import Arquitetura.Dao.UsuarioDAO;
import Arquitetura.Model.Administrador;
import Arquitetura.Model.Medico;

public class MedicoService {

    // -- Atributos -- //
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

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

    // Deleta medico do banco de dados
    public boolean deletarMedico(Administrador administrador, Medico medico) {

        if(funcionarioService.deletarFuncionario(medico.getId())) {
            medicoDAO.deletarMedico(medico.getId());
            funcionarioDAO.deletarFuncionario(medico.getId());
            usuarioDAO.deletarUsuario(medico.getId());

            return true;
        }

        return false;
    }
}
